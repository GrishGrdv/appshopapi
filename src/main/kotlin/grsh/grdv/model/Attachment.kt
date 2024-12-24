package grsh.grdv.model

import io.micronaut.data.annotation.*
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.PageableRepository
import io.micronaut.data.repository.reactive.ReactorPageableRepository

@MappedEntity("attachment")
data class Attachment(
    @Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    val id: Long,

    val name: String,

    val path: String,
)

@JdbcRepository(dialect = Dialect.POSTGRES)
abstract class  AttachmentRepo : PageableRepository<Attachment, Long> {

}