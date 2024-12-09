package com.skyfe.repository

import com.skyfe.domain.model.User

interface UserRepository: BaseRepository<User> {
    fun findByUsername(username: String): User
}