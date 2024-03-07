package com.hcindy.chickenfeetsvc.controller

import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import com.hcindy.chickenfeetsvc.dto.StandardResponseDTO
import com.hcindy.chickenfeetsvc.dto.fail
import com.hcindy.chickenfeetsvc.exception.FailedException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

// 并不能处理乱写URI的情况，该问题由resources\public\error\404.html处理，或按官方文档修改
@RestControllerAdvice
class GlobalExceptionHandler {
    private val logger = LoggerFactory.getLogger(javaClass)
    private val UNCOVERED_EXCEPTION_MESSAGE = "Uncovered exception"

    @ExceptionHandler(FailedException::class)
    fun failedExceptionHandler(e: FailedException): StandardResponseDTO<Any> = fail(e.message)

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun httpMessageNotReadableExceptionHandler(e: HttpMessageNotReadableException): StandardResponseDTO<Any> =
        when (e.cause) {
            is MissingKotlinParameterException -> fail("illegal argument").also { logger.warn(e.message) }
            else -> fail().also { logger.error(UNCOVERED_EXCEPTION_MESSAGE, e) }
        }

    @ExceptionHandler(Throwable::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun throwableHandler(e: Throwable): StandardResponseDTO<Any> =
        fail().also { logger.error(UNCOVERED_EXCEPTION_MESSAGE, e) }
}
