package grsh.grdv.security.service

import grsh.grdv.model.UserInfo
import grsh.grdv.model.UserInfoRepo
import grsh.grdv.security.utils.SecurityUtils
import io.micronaut.core.annotation.NonNull
import io.micronaut.core.annotation.Nullable
import io.micronaut.http.HttpRequest
import io.micronaut.security.authentication.Authentication
import io.micronaut.security.authentication.AuthenticationFailureReason
import io.micronaut.security.authentication.AuthenticationRequest
import io.micronaut.security.authentication.AuthenticationResponse
import io.micronaut.security.authentication.provider.HttpRequestAuthenticationProvider
import jakarta.inject.Singleton


@Singleton
internal class AuthenticationProviderUserPassword<B>(
    private val userInfoRepo: UserInfoRepo
) : HttpRequestAuthenticationProvider<B> {


    override fun authenticate(
        @Nullable httpRequest: HttpRequest<B>?,
        @NonNull authenticationRequest: AuthenticationRequest<String, String>
    ): AuthenticationResponse {
        val userInfo = userInfoRepo.findByName(authenticationRequest.identity)

        return if (userInfo == null) {
            AuthenticationResponse.failure(AuthenticationFailureReason.USER_NOT_FOUND)
        } else if (SecurityUtils.verify(authenticationRequest.secret, userInfo.password)) {
            AuthenticationResponse.success(authenticationRequest.identity, listOf(userInfo.role.name))
        } else {
            AuthenticationResponse.failure(AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH)
        }
    }
}