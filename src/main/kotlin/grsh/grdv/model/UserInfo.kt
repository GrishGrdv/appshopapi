package grsh.grdv.model

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.annotation.Relation
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.PageableRepository
import io.micronaut.data.repository.reactive.ReactorPageableRepository

@MappedEntity("user_info")
data class UserInfo(
    @Id
    @GeneratedValue
    val id: Long,
    val name: String,
    val email: String,
    val password: String,

    @Relation(value = Relation.Kind.ONE_TO_MANY, mappedBy = "recipient")
    val recipient: List<Recipient>,
)

@JdbcRepository(dialect = Dialect.POSTGRES)
abstract class  UserInfoRepo : PageableRepository<UserInfo, Long> {

}