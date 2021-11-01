package me.jadjer.ansem.data.repository

import me.jadjer.ansem.data.model.entity.RequestEntity
import me.jadjer.ansem.data.model.entity.SessionEntity

interface SessionRepository {
    suspend fun getActive(): List<SessionEntity>
    suspend fun getAll(): List<SessionEntity>
    suspend fun remove(sessionId: Int): Boolean
//    suspend fun create()
    suspend fun get(sessionId: Int): SessionEntity
    suspend fun create(name: String, description: String, date_start: String, date_end: String)
}