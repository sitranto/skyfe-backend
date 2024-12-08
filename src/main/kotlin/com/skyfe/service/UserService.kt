package com.skyfe.service

import com.skyfe.domain.model.User
import com.skyfe.middlewhare.DataNotFoundException
import com.skyfe.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class UserService(private val userRepository: UserRepository) {
    fun getUser(id: Int): Optional<User> =
        userRepository.findById(id)
            ?: throw DataNotFoundException(HttpStatus.NOT_FOUND, "User with id $id not found")

    fun createUser(user: User): User =
        userRepository.save(user)

    fun deleteUser(id: Int) =
        userRepository.delete(id)
}