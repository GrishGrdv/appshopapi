package grsh.grdv.controller

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import java.security.Principal


@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/persons")
class TestController {

    @Produces(MediaType.TEXT_PLAIN)
    @Get
    fun index(principal: Principal): String {
        return principal.name
    }
}