package grsh.grdv.model

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.annotation.Relation
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.PageableRepository
import io.micronaut.data.repository.reactive.ReactorPageableRepository
import java.math.BigDecimal

@MappedEntity("good")
data class Good(
    @Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    val id: Long,

    val name: String,

    val description: String,

    val price: BigDecimal,

    @Relation(value = Relation.Kind.MANY_TO_ONE)
    val category: GoodCategory,
)

@JdbcRepository(dialect = Dialect.POSTGRES)
abstract class  GoodRepo : PageableRepository<Good, Long> {

}