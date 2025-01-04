package grsh.grdv.service.email

import grsh.grdv.service.email.dto.EmailMessage
import io.micronaut.configuration.kafka.annotation.KafkaClient
import io.micronaut.configuration.kafka.annotation.Topic
import io.micronaut.http.annotation.Body

@KafkaClient
interface MessageSender {

    @Topic("/user-message")
    fun sendUserMessage(@Body emailMessage: EmailMessage) {}

    @Topic("/order-message")
    fun sendOrderMessage(@Body emailMessage: EmailMessage) {}
}