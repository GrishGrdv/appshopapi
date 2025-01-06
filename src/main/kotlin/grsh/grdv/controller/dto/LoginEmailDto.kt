package grsh.grdv.controller.dto

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class LoginEmailDto(
    var email: String
) {
}