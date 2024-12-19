package com.skyfe.service

import com.skyfe.domain.model.User
import com.skyfe.middlewhare.DataAlreadyExistsException
import com.skyfe.middlewhare.DataNotFoundException
import com.skyfe.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.io.Console
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
        if(checkNumber(user.number) == HttpStatus.CONFLICT)
            throw DataAlreadyExistsException(HttpStatus.CONFLICT, "Number already exists")
        if(checkUsername(user.username) == HttpStatus.CONFLICT)
            throw DataAlreadyExistsException(HttpStatus.CONFLICT, "Username already exists")
        user.password = encoder.encode(user.password)
        userRepository.save(user)
        return user
    }

    fun checkNumber(number: String): HttpStatus {
        return if(userRepository.existsByNumber(number))
            HttpStatus.CONFLICT
        else
            HttpStatus.OK
    }

    fun checkUsername(username: String): HttpStatus {
        return if(userRepository.existsByUsername(username))
            HttpStatus.CONFLICT
        else
            HttpStatus.OK
    }

    fun deleteUser(id: Int) =
        userRepository.delete(id)

}