package grsh.grdv.security.service

import io.micronaut.core.annotation.NonNull
import io.micronaut.core.annotation.Nullable
import io.micronaut.http.HttpRequest
import io.micronaut.security.authentication.AuthenticationFailureReason
import io.micronaut.security.authentication.AuthenticationRequest
import io.micronaut.security.authentication.AuthenticationResponse
import io.micronaut.security.authentication.provider.HttpRequestAuthenticationProvider
import jakarta.inject.Singleton


@Singleton
internal class AuthenticationProviderUserPassword<B> : HttpRequestAuthenticationProvider<B> {
    override fun authenticate(
        @Nullable httpRequest: HttpRequest<B>?,
        @NonNull authenticationRequest: AuthenticationRequest<String, String>
    ): AuthenticationResponse {
        return if (authenticationRequest.identity == "sherlock" && authenticationRequest.secret == "password")
            AuthenticationResponse.success(authenticationRequest.identity)
        else
            AuthenticationResponse.failure(AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH)
    }
}