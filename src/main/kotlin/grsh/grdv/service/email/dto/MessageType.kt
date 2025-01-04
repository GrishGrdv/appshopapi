package grsh.grdv.service.email.dto

enum class MessageType(
    val pathTemplateTitle: String,
    val pathTemplateContent: String
) {
    CREATE_ORDER("", ""),
    REVOKE_ORDER("", ""),
    SEND_OTP("", "")
}