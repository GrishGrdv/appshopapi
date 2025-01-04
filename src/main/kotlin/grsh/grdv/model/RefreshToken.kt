package grsh.grdv.model

import io.micronaut.data.annotation.DateCreated
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.PageableRepository
import io.micronaut.data.repository.reactive.ReactorPageableRepository
import java.time.LocalDateTime
import java.util.*

@MappedEntity(value = "refresh_token")
data class RefreshToken(
    @field:Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    var id: Long,

    var username: String,
    var refreshToken: String,
    var revoked: Boolean,

    @DateCreated
    var dateCreated: LocalDateTime?,
)

@JdbcRepository(dialect = Dialect.POSTGRES)
abstract class  RefreshTokenRepo : PageableRepository<RefreshToken, Long> {
    abstract fun findByRefreshToken(token: String): Optional<RefreshToken>

    abstract fun updateByUsername(username: String, revoked: Boolean)
}