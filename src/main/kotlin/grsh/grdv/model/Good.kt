package grsh.grdv.model

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Relation
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.PageableRepository
import io.micronaut.data.repository.reactive.ReactorPageableRepository
import java.math.BigDecimal
import io.micronaut.serde.annotation.Serdeable

@Serdeable
@MappedEntity("good")
data class Good(
    @field:Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    val id: Long,

    var name: String = "",

    var description: String = "",

    var price: BigDecimal = BigDecimal.ZERO,

    @Relation(value = Relation.Kind.MANY_TO_ONE)
    var category: GoodCategory? = null,
)

@JdbcRepository(dialect = Dialect.POSTGRES)
abstract class  GoodRepo : PageableRepository<Good, Long> {

    @Query("SELECT * FROM good WHERE category in :category AND (name ilike :name OR description ilike :name) " +
            "AND price > :minPrice AND price < :maxPrice ORDER BY :")
    abstract fun findAllByCategoryAndSearchParameters(
        category: List<Long>,
        name: String,
        maxPrice: BigDecimal,
        minPrice: BigDecimal,
        pageable: Pageable
    ): Page<Good>

    @Query("SELECT * FROM good WHERE (name ilike :name OR description ilike :name) AND price > :minPrice AND price < :maxPrice")
    abstract fun findAllBySearchParameters(category: Long, pageable: Pageable): Page<Good>
}