package com.hcindy.chickenfeetsvc.interceptor

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class TopInterceptor : HandlerInterceptor {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        request.setAttribute("startTime", System.currentTimeMillis())
        return true
    }

    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?
    ) {
        // FIXME log time just for example
        // If need mature monitor data, use org.springframework.boot:spring-boot-starter-actuator
        logger.info("${request.requestURL} spend ${System.currentTimeMillis() - request.getAttribute("startTime") as Long} ms")
    }
}
