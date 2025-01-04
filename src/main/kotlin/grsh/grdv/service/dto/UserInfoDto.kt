package grsh.grdv.service.dto

import grsh.grdv.model.UserInfo

data class UserInfoDto(
    val id: Long,
    val name: String,
    val email: String,
    val role: String,
    val verifyEmail: Boolean
) {
}