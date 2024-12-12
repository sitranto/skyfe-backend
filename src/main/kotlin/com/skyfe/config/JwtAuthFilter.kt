package com.skyfe.config

import com.skyfe.service.CustomUserDetailsService
import com.skyfe.service.JwtService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthFilter(
    private val userDetailsService: CustomUserDetailsService,
    private val jwtService: JwtService
    ): OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader: String? = request.getHeader("Authorization")
        if(authHeader.doesNotContainBearer()) {
            filterChain.doFilter(request, response)
            return
        }

        val token = authHeader!!.extractToken()
        val number = jwtService.extractNumber(token)

        if(number != null && SecurityContextHolder.getContext().authentication == null) {
            val foundUser = userDetailsService.loadUserByUsername(number)

            if(jwtService.isValid(token, foundUser))
                updateContext(foundUser, request)

            filterChain.doFilter(request, response)
        }
    }

    private fun String?.doesNotContainBearer(): Boolean =
        this == null || !this.startsWith("Bearer ")

    private fun String.extractToken(): String =
        this.substringAfter("Bearer ")

    private fun updateContext(foundUser: UserDetails, request: HttpServletRequest) {
        val authToken = UsernamePasswordAuthenticationToken(foundUser, null, foundUser.authorities)
        authToken.details = WebAuthenticationDetailsSource().buildDetails(request)

        SecurityContextHolder.getContext().authentication = authToken
    }
}