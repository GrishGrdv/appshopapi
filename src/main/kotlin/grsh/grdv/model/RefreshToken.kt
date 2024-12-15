package grsh.grdv.model

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.*
import io.micronaut.data.repository.CrudRepository
import io.micronaut.transaction.annotation.Transactional
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime
import java.util.*

@MappedEntity("refresh_token")
data class RefreshToken(
    @Id
    @GeneratedValue
    val id: Long?,

    val username: String,
    val refreshToken: String,
    val revoked: Boolean,

    @DateCreated
    val dateCreated: LocalDateTime?,
) {

    companion object {
        @Repository
        interface Repo : CrudRepository<RefreshToken, Long?> {

            fun findByRefreshToken(token: String): Optional<RefreshToken>

            fun updateByUsername(@NonNull username: String, @NonNull revoked: Boolean)

            @Transactional
            fun save(
                username: @NonNull @NotBlank String?,
                refreshToken: @NonNull @NotBlank String?,
                revoked: @NonNull @NotNull Boolean?
            ): RefreshToken?
        }
    }
}

@Repository
interface RefreshTokenRepo : CrudRepository<RefreshToken, Long?> {

    fun findByRefreshToken(token: String): Optional<RefreshToken>

    fun updateByUsername(username: String, revoked: Boolean)

    @Transactional
    fun save(refreshToken: RefreshToken): RefreshToken?
}