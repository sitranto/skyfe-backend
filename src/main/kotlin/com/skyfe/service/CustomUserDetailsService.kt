package com.skyfe.service

import com.skyfe.middlewhare.DataNotFoundException
import com.skyfe.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService

typealias ApplicationUser = com.skyfe.domain.model.User

class CustomUserDetailsService(private val userRepository: UserRepository): UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails =
        userRepository.findByUsername(username)
            ?.mapToUserDetails()
            ?: throw DataNotFoundException(HttpStatus.NOT_FOUND, "User $username not found")

    private fun ApplicationUser.mapToUserDetails(): UserDetails =
        User.builder()
            .username(this.username)
            .password(this.password)
            .build()
}