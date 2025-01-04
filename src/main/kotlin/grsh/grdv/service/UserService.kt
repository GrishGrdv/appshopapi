package grsh.grdv.service

import grsh.grdv.model.*
import grsh.grdv.service.email.MessageSender
import grsh.grdv.service.email.dto.EmailMessage
import grsh.grdv.service.email.dto.MessageType
import io.micronaut.cache.annotation.CacheConfig
import io.micronaut.cache.annotation.CachePut
import io.micronaut.configuration.lettuce.cache.RedisCache
import io.micronaut.transaction.annotation.Transactional
import jakarta.inject.Named
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory
import java.util.UUID

@CacheConfig(cacheNames = ["appshopapi"])
@Singleton
class UserService(
    private val userInfoRepo: UserInfoRepo,
    private val refreshTokenRepo: RefreshTokenRepo,
    private val messageSender: MessageSender,
    private val cacheOtp: RedisCache
) {
    private val log = LoggerFactory.getLogger(this::class.java)
    private val otpCodes = HashMap<String, String>()

    fun sendOtpEmail(email: String): Boolean {
        var userInfo = userInfoRepo.findByEmail(email)
        if (userInfo == null) {
            userInfo = UserInfo(email, UserInfo.Companion.Role.USER)
            userInfoRepo.save(userInfo)
        }

        log.info("sendOtpEmail userInfo: $userInfo")
        val otp = UUID.randomUUID().toString()
        cacheOtp.put(email, otp)
        messageSender.sendUserMessage(EmailMessage(MessageType.SEND_OTP, userInfo.id ?: -1, mapOf()))

        return true
    }

}