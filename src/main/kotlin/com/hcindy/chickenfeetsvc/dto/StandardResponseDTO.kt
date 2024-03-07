package com.hcindy.chickenfeetsvc.dto

data class StandardResponseDTO<T>(
    val result: Int,
    val message: String,
    val data: T?
)

fun <T> ok(data: T? = null): StandardResponseDTO<T> = StandardResponseDTO(0, "", data)

fun fail(message: String? = null): StandardResponseDTO<Any> = StandardResponseDTO(-1, message ?: "未知错误", null)
