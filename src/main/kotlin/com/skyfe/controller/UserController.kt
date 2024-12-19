package com.skyfe.controller

import com.skyfe.domain.dto.AuthDto
import com.skyfe.domain.dto.AuthResponse
import com.skyfe.domain.dto.UserDto
import com.skyfe.domain.model.User
import com.skyfe.service.AuthService
import com.skyfe.service.UserService
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
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
        val user = userService.createUser(payload)
        return authService.authentication(AuthDto(user.number, user.password))
    }

    @PostMapping("/number")
    fun checkNumber(@RequestBody payload: UserDto): HttpStatus =
        userService.checkNumber(payload.number.toString())

    @PostMapping("/username")
    fun checkUsername(@RequestBody payload: UserDto): HttpStatus =
        userService.checkUsername(payload.username.toString())

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable("id") id: Int) =
        userService.deleteUser(id)
}