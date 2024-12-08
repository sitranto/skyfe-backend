package com.skyfe.repository

import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
@JvmDefaultWithCompatibility
interface BaseRepository<T>: JpaRepository<T, Int?> {
    @Transactional
    @Modifying
    @Query("DELETE FROM #{#entityName} e WHERE e.id=:id")

    fun delete(id: Int): Int

    fun deleteExisted(id: Int) {
        if (delete(id) == 0) {
            throw Exception("Entity with id=$id not found")
        }
    }

    fun getExisted(id: Int): T = findById(id).orElseThrow { Exception("Entity with id=$id not found") }
}