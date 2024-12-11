package com.skyfe.service

import com.skyfe.middlewhare.DataNotFoundException
import com.skyfe.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

typealias ApplicationUser = com.skyfe.domain.model.User

@Service
class CustomUserDetailsService(private val userRepository: UserRepository): UserDetailsService {
    override fun loadUserByUsername(number: String): UserDetails =
        userRepository.findByNumber(number)
            ?.mapToUserDetails()
            ?: throw DataNotFoundException(HttpStatus.NOT_FOUND, "User with number $number not found")

    private fun ApplicationUser.mapToUserDetails(): UserDetails =
        User.builder()
            .username(this.number)
            .password(this.password)
            .build()
}