package grsh.grdv.controller

import grsh.grdv.controller.dto.LoginEmailDto
import grsh.grdv.service.UserService
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import jakarta.annotation.security.PermitAll

@PermitAll
@Controller("/auth")
class AuthController(
    private val userService: UserService
) {

    @Post("/login")
    fun login(@Body loginData: LoginEmailDto) {
        userService.sendOtpEmail(loginData.email)
    }
}