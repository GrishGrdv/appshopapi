package grsh.grdv.service.email.dto

data class EmailMessage(
    val messageType: MessageType,
    val modelId: Long,
    val args: Map<String, Any>
) {
}