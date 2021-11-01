package me.jadjer.ansem.data.repository

import me.jadjer.ansem.data.api.SessionApi
import me.jadjer.ansem.data.model.api.Session
import me.jadjer.ansem.data.model.entity.SessionEntity

class SessionRepositoryImpl(private val sessionApi: SessionApi) : SessionRepository {

    override suspend fun getActive(): List<SessionEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun getAll(): List<SessionEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun remove(sessionId: Int): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun get(sessionId: Int): SessionEntity {
        TODO("Not yet implemented")
    }

    override suspend fun create(
        name: String,
        description: String,
        date_start: String,
        date_end: String
    ) {
        sessionApi.create(
            Session(
                name = name,
                description = description,
                date_start = date_start,
                date_end = date_end
            )
        )
    }
}