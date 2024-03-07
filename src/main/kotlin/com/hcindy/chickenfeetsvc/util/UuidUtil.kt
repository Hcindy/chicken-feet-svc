package com.hcindy.chickenfeetsvc.util

import java.util.*

fun createUuid(): String =
    UUID.randomUUID()
        .toString()
        .replace("-", "")
        .substring(0, 16)
