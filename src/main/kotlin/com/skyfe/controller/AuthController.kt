package com.skyfe.controller

import com.skyfe.domain.dto.AuthDto
import com.skyfe.domain.dto.AuthResponse
import com.skyfe.service.AuthService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(private val authService: AuthService) {

    @PostMapping
    fun authentication(@RequestBody authRequest: AuthDto): AuthResponse =
        authService.authentication(authRequest)
}