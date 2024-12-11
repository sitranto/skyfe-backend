package com.skyfe.service

import com.skyfe.config.JwtConfiguration
import com.skyfe.domain.dto.AuthDto
import com.skyfe.domain.dto.AuthResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthService(
    private val authManager: AuthenticationManager,
    private val userDetailsService: CustomUserDetailsService,
    private val jwtService: JwtService,
    private val jwtConfiguration: JwtConfiguration
) {
    fun authentication(authRequest: AuthDto): AuthResponse {
        authManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authRequest.number,
                authRequest.password
            )
        )

        val user = userDetailsService.loadUserByUsername(authRequest.number)
        val accessToken = jwtService.generateToken(
            userDetails = user,
            expirationDate = Date(System.currentTimeMillis() + jwtConfiguration.accessTokenExpiration))

        return AuthResponse(accessToken = accessToken)
    }
}