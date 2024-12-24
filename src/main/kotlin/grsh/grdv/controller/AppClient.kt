package grsh.grdv.controller

import io.micronaut.http.HttpHeaders
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.http.client.annotation.Client
import io.micronaut.security.authentication.UsernamePasswordCredentials
import io.micronaut.security.token.render.BearerAccessRefreshToken


@Client("/")
interface AppClient {
    @Post("/login")
    fun login(@Body credentials: UsernamePasswordCredentials?): BearerAccessRefreshToken?

    @Consumes(MediaType.TEXT_PLAIN)
    @Get
    fun home(@Header(HttpHeaders.AUTHORIZATION) authorization: String?): String?
}