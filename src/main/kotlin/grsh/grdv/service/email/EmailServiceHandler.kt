package grsh.grdv.service.email

import grsh.grdv.model.CheckoutRepo
import grsh.grdv.model.UserInfoRepo
import grsh.grdv.service.email.dto.EmailMessage
import io.micronaut.configuration.kafka.annotation.KafkaListener
import io.micronaut.configuration.kafka.annotation.Topic
import io.micronaut.email.Email
import io.micronaut.email.EmailSender
import io.micronaut.email.MultipartBody
import io.micronaut.http.annotation.Body

@KafkaListener
internal class EmailServiceHandler(
    private val userInfoRepo: UserInfoRepo,
    private val checkoutRepo: CheckoutRepo,
    private val emailSender: EmailSender<Any, Any>
) {
    // TODO будет работать по слушателю топика (kafka)

    @Topic("/user-message")
    fun userMessageHandle(@Body emailMessage: EmailMessage) {
        val userInfo = userInfoRepo.findById(emailMessage.modelId)
        if (userInfo.isEmpty) {
            return
        }

        val binding = mapOf<String, Any>(Pair("userInfo", userInfo)) + emailMessage.args
        val titleEmail = TemplateUtils.buildContentByTemplate(emailMessage.messageType.pathTemplateTitle, binding)
        val contentEmail = TemplateUtils.buildContentByTemplate(emailMessage.messageType.pathTemplateContent, binding)

        emailSender.send(Email.builder()
            .from("sender@example.com")
            .to("john@example.com")
            .subject(titleEmail)
            .body(MultipartBody(contentEmail, titleEmail))
        )
    }

    @Topic("/order-message")
    fun orderMessageHandle(@Body emailMessage: EmailMessage) {
        val checkout = checkoutRepo.findById(emailMessage.modelId)
        if (checkout.isEmpty) {
            return
        }

        val binding = mapOf<String, Any>(Pair("checkout", checkout)) + emailMessage.args
        val titleEmail = TemplateUtils.buildContentByTemplate(emailMessage.messageType.pathTemplateTitle, binding)
        val contentEmail = TemplateUtils.buildContentByTemplate(emailMessage.messageType.pathTemplateContent, binding)

        emailSender.send(Email.builder()
            .from("sender@example.com")
            .to("john@example.com")
            .subject(titleEmail)
            .body(MultipartBody(contentEmail, titleEmail))
        )
    }
}