package com.hcindy.chickenfeetsvc.config

import com.hcindy.chickenfeetsvc.interceptor.TopInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfig(private val topInterceptor: TopInterceptor) : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(topInterceptor).addPathPatterns("/**")
    }
}
