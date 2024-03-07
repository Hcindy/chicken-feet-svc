package com.hcindy.chickenfeetsvc.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class HtmlController {
    // 网页html5mode路由刷新界面重定向到网页
    @RequestMapping(
        value = ["/home",
            "/register",
            "/hall",
            "/waiting",
            "/gaming/**",
            "/personal-game-result",
            "/manage-game",
            "/new-game",
            "/set-game/**",
            "/show-game/**"]
    )
    fun redirectWR(): String = "forward:/index.html"
}