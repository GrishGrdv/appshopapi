package grsh.grdv.security

import com.nimbusds.jwt.JWTParser
import com.nimbusds.jwt.SignedJWT
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.security.authentication.UsernamePasswordCredentials
import io.micronaut.security.token.render.BearerAccessRefreshToken
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.text.ParseException


@MicronautTest
internal class JwtAuthenticationTest {

    @Inject
    @Client("/")
    var client: HttpClient? = null

    @Test
    fun accessingASecuredUrlWithoutAuthenticatingReturnsUnauthorized() {
        val e = Assertions.assertThrows(
            HttpClientResponseException::class.java
        ) {
            client!!.toBlocking().exchange<Any, Any>(
                HttpRequest.GET<Any>("/").accept(MediaType.TEXT_PLAIN)
            )
        }

        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, e.status)
    }

    @Test
    @Throws(ParseException::class)
    fun uponSuccessfulAuthenticationAJsonWebTokenIsIssuedToTheUser() {
        val creds = UsernamePasswordCredentials("sherlock", "password")
        val request: HttpRequest<*> = HttpRequest.POST("/login", creds)
        val rsp = client!!.toBlocking().exchange(
            request,
            BearerAccessRefreshToken::class.java
        )
        Assertions.assertEquals(HttpStatus.OK, rsp.status)

        val bearerAccessRefreshToken = rsp.body()
        Assertions.assertEquals("sherlock", bearerAccessRefreshToken.username)
        Assertions.assertNotNull(bearerAccessRefreshToken.accessToken)
        Assertions.assertTrue(JWTParser.parse(bearerAccessRefreshToken.accessToken) is SignedJWT)

        val accessToken = bearerAccessRefreshToken.accessToken
        val requestWithAuthorization: HttpRequest<*> = HttpRequest.GET<Any>("/")
            .accept(MediaType.TEXT_PLAIN)
            .bearerAuth(accessToken)
        val response = client!!.toBlocking().exchange(
            requestWithAuthorization,
            String::class.java
        )

        Assertions.assertEquals(HttpStatus.OK, rsp.status)
        Assertions.assertEquals("sherlock", response.body())
    }
}