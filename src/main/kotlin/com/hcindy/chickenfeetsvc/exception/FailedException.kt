package com.hcindy.chickenfeetsvc.exception

// 调用rest接口，业务逻辑错误或失败时，则以抛FailedException或其子类异常的方式处理
typealias FailedException = RuntimeException
