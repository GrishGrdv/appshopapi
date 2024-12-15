package grsh.grdv.security.service

import grsh.grdv.model.RefreshToken
import grsh.grdv.model.RefreshTokenRepo
import io.micronaut.security.authentication.Authentication
import io.micronaut.security.errors.IssuingAnAccessTokenErrorCode.INVALID_GRANT
import io.micronaut.security.errors.OauthErrorResponseException
import io.micronaut.security.token.event.RefreshTokenGeneratedEvent
import io.micronaut.security.token.refresh.RefreshTokenPersistence
import jakarta.inject.Singleton
import org.reactivestreams.Publisher
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink
import java.util.*
import java.util.function.Consumer


@Singleton
class CustomRefreshTokenPersistence(private val refreshTokenRepository: RefreshTokenRepo) : RefreshTokenPersistence {

    override fun persistToken(event: RefreshTokenGeneratedEvent) {

        if (event.refreshToken != null && event.authentication != null && event.authentication.name != null) {
            val payload = event.refreshToken
            refreshTokenRepository.save(RefreshToken(
                id = null,
                username = event.authentication.name,
                refreshToken = event.refreshToken,
                revoked = false,
                dateCreated = null
            ))
        }
    }

    override fun getAuthentication(refreshToken: String?): Publisher<Authentication?> {
        return Flux.create<Authentication?>({ emitter: FluxSink<Authentication?> ->
            val tokenOpt: Optional<RefreshToken> = refreshTokenRepository.findByRefreshToken(refreshToken!!)

            if (!tokenOpt.isPresent()) {
                emitter.error(OauthErrorResponseException(INVALID_GRANT, "refresh token not found", null))
            }

            val token: RefreshToken = tokenOpt.get()
            if (token.revoked) {
                emitter.error(OauthErrorResponseException(INVALID_GRANT, "refresh token revoked", null))
            } else {
                emitter.next(Authentication.build(token.username))
                emitter.complete()
            }
        }, FluxSink.OverflowStrategy.ERROR)
    }
}