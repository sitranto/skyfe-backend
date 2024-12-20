package com.skyfe.config

import org.apache.catalina.webresources.TomcatURLStreamHandlerFactory.disable
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration(private val authenticationProvider: AuthenticationProvider) {
    @Bean
    fun filterChain(
        http: HttpSecurity,
        jwtAuthFilter: JwtAuthFilter
    ): DefaultSecurityFilterChain =
        http
            .csrf().disable()
            .cors().disable()
            .authorizeHttpRequests {
                it
                    .requestMatchers("/api/auth", "/api/user", "/api/user/number", "/api/user/username").permitAll()
                    .requestMatchers("/api/**").authenticated()
            }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
}
