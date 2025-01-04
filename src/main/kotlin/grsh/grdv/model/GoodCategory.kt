package grsh.grdv.model

import io.micronaut.data.annotation.*
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.PageableRepository
import io.micronaut.data.repository.reactive.ReactorPageableRepository
import io.micronaut.serde.annotation.Serdeable

@Serdeable
@MappedEntity("good_category")
data class GoodCategory(
    @field:Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    val id: Long,

    var name: String = "",

    var description: String = "",

    @Relation(value = Relation.Kind.ONE_TO_MANY, mappedBy = "good_category")
    var goods: List<Good> = listOf(),
)

@JdbcRepository(dialect = Dialect.POSTGRES)
abstract class  GoodCategoryRepo : PageableRepository<GoodCategory, Long> {

}