package me.jadjer.ansem.data.repository

import me.jadjer.ansem.data.model.entity.RequestEntity

interface RequestRepository {
    suspend fun getAll(): List<RequestEntity>
    suspend fun remove(requestId: Int): Boolean
    suspend fun create(
        school: String,
        classNo: String,
        score: Double,
        index: Int,
        sessionId: Int
    ): Boolean
}