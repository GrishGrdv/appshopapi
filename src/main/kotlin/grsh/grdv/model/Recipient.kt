package grsh.grdv.model

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.annotation.Relation
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.PageableRepository
import io.micronaut.data.repository.reactive.ReactorPageableRepository
import io.micronaut.serde.annotation.Serdeable

@Serdeable
@MappedEntity("recipient")
data class Recipient(
    @Id
    @GeneratedValue()
    val id: Long,
    val firstName: String,
    val lastName: String,
    val middleName: String,
    val address: String,
    val zipCode: String,
    val phone: String,
    @Relation(value = Relation.Kind.MANY_TO_ONE)
    val userInfo: UserInfo,
)

@JdbcRepository(dialect = Dialect.POSTGRES)
abstract class  RecipientRepo : PageableRepository<Recipient, Long> {

}