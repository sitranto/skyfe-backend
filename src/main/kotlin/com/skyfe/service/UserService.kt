package com.skyfe.service

import com.skyfe.domain.model.User
import com.skyfe.middlewhare.DataNotFoundException
import com.skyfe.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val encoder: PasswordEncoder
) {
    fun getUser(id: Int): Optional<User> =
        userRepository.findById(id)
            ?: throw DataNotFoundException(HttpStatus.NOT_FOUND, "User with id $id not found")

    fun createUser(user: User): User {
        user.password = encoder.encode(user.password)
        userRepository.save(user)
        return user
    }

    fun deleteUser(id: Int) =
        userRepository.delete(id)

}