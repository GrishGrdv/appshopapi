package grsh.grdv.model

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.annotation.Relation
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.PageableRepository
import io.micronaut.serde.annotation.Serdeable

@Serdeable
@MappedEntity("user_info")
class UserInfo(
    @field:Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    var id: Long?,
    var name: String?,
    var email: String,
    var role: Role,

    @Relation(value = Relation.Kind.ONE_TO_MANY, mappedBy = "recipient")
    var recipient: List<Recipient>,
) {
    constructor(email: String, role: Role): this(null, null, email, role, listOf())

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
    abstract fun findByEmail(email: String): UserInfo?
}