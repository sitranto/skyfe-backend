package com.skyfe.controller

import com.skyfe.domain.model.User
import com.skyfe.service.UserService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
class UserController(private val userService: UserService) {
    @GetMapping("/{id}")
    fun getUser(@PathVariable("id") id: Int): Optional<User> =
        userService.getUser(id)

    @PostMapping
    fun createUser(@RequestBody payload: User): User =
        userService.createUser(payload)

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable("id") id: Int) =
        userService.deleteUser(id)
}