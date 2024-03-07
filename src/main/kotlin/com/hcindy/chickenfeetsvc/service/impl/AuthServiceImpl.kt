package com.hcindy.chickenfeetsvc.service.impl

import com.hcindy.chickenfeetsvc.dto.AccountDTO
import com.hcindy.chickenfeetsvc.exception.FailedException
import com.hcindy.chickenfeetsvc.service.AccountService
import com.hcindy.chickenfeetsvc.service.AuthService
import com.hcindy.chickenfeetsvc.util.createUuid
import org.slf4j.LoggerFactory
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class AuthServiceImpl(
    private val accountService: AccountService,
    private val stringRedisTemplate: StringRedisTemplate
) : AuthService {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun login(accountDTO: AccountDTO): String {
        if (accountService.existAccount(accountDTO)) {
            while (true) {
                val token = createUuid()
                try {
                    // FIXME 判断已登录
                    val res = stringRedisTemplate
                        .opsForValue()
                        .setIfAbsent(
                            "token:$token",
                            "account:${accountDTO.username}:username",
                            30,
                            TimeUnit.MINUTES
                        )
                    if (res == true) return token
                } catch (e: Exception) {
                    logger.error(e.message, e)
                    throw FailedException("error")
                }
            }
        } else {
            throw FailedException("does not exist")
        }
    }

    override fun logout(token: String) {
        logger.info("token: $token")
        if (!stringRedisTemplate.delete("token:$token")) logger.warn("delete $token fail")
    }

    override fun getAccountUsername(token: String): String? {
        return stringRedisTemplate
            .opsForValue()
            .get("token:$token")
            ?.let { it.split(":")[1] }
    }

}
