package com.skyfe.controller

import com.skyfe.domain.dto.AuthDto
import com.skyfe.domain.dto.AuthResponse
import com.skyfe.domain.model.User
import com.skyfe.service.AuthService
import com.skyfe.service.UserService
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
class UserController(private val userService: UserService, private val authService: AuthService) {
    @GetMapping("/{id}")
    fun getUser(@PathVariable("id") id: Int): Optional<User> =
        userService.getUser(id)

    @PostMapping
    fun createUser(@RequestBody payload: User): AuthResponse {
        userService.createUser(payload)
        return authService.authentication(AuthDto(payload.number, payload.password))
    }

    @PostMapping("/number")
    fun checkNumber (@RequestBody payload: String): HttpStatus =
        userService.checkNumber(payload)

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable("id") id: Int) =
        userService.deleteUser(id)
}