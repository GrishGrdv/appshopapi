package grsh.grdv.model

import io.micronaut.data.annotation.*
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.PageableRepository
import io.micronaut.data.repository.reactive.ReactorPageableRepository

@MappedEntity("good_category")
data class GoodCategory(
    @Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    val id: Long,

    val name: String,

    val description: String,

    @Relation(value = Relation.Kind.ONE_TO_MANY, mappedBy = "good_category")
    val goods: List<Good>,
)

@JdbcRepository(dialect = Dialect.POSTGRES)
abstract class  GoodCategoryRepo : PageableRepository<GoodCategory, Long> {

}