package grsh.grdv.model

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.annotation.Relation
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.PageableRepository

@MappedEntity("user_info")
data class UserInfo(
    @Id
    @GeneratedValue
    var id: Long,
    var name: String,
    var email: String,
    var password: String,
    var role: Role,

    @Relation(value = Relation.Kind.ONE_TO_MANY, mappedBy = "recipient")
    var recipient: List<Recipient>,
) {
    companion object {
        enum class Role {
            ADMIN,
            USER
        }
    }
}

@JdbcRepository(dialect = Dialect.POSTGRES)
abstract class  UserInfoRepo : PageableRepository<UserInfo, Long> {
    abstract fun findByName(name: String): UserInfo?
}