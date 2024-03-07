package com.hcindy.chickenfeetsvc.controller

import com.hcindy.chickenfeetsvc.dto.StandardResponseDTO
import com.hcindy.chickenfeetsvc.dto.ok
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1/qr")
@CrossOrigin
class QrController {
    @GetMapping
    fun getQr(): StandardResponseDTO<String> =
//        ok("http://192.168.0.10/hall")
        ok("http://192.168.0.10:50000/hall")

}
