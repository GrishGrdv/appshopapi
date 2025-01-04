package grsh.grdv.model

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Relation
import io.micronaut.data.jdbc.annotation.JdbcRepository
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

    @Query("SELECT * FROM good WHERE category = :category")
    abstract fun findAllByCategory(category: Long, pageable: Pageable): List<Good>
}