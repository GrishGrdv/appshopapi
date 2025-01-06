package grsh.grdv.config

import io.micronaut.context.annotation.Property
import io.micronaut.email.javamail.sender.MailPropertiesProvider
import io.micronaut.email.javamail.sender.SessionProvider
import jakarta.inject.Singleton
import jakarta.mail.Authenticator
import jakarta.mail.PasswordAuthentication
import jakarta.mail.Session
import java.util.*

@Singleton
class OciSessionProvider(
    @Property(name = "javamail.authentication.username") val user: String,
    @Property(name = "javamail.authentication.password") val password: String
) : SessionProvider {



    override fun session(): Session {
        val emailProperties = Properties()
        emailProperties["mail.transport.protocol"] = "smtps"
        emailProperties["mail.smtp.auth"] = true
        emailProperties["mail.smtp.host"] = "smtp.yandex.ru"
        emailProperties["mail.smtp.port"] = 465
        emailProperties["mail.smtp.user"] = "grigorygrodov"

        return Session.getInstance(emailProperties, object : Authenticator() {
            override fun getPasswordAuthentication() = PasswordAuthentication(user, password)
        })
    }
}