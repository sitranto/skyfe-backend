package com.skyfe.repository

import com.skyfe.domain.model.User

interface UserRepository: BaseRepository<User> {
    fun existsByUsername(username: String): Boolean
    fun findByNumber(number: String): User
    fun existsByNumber(number: String): Boolean
}