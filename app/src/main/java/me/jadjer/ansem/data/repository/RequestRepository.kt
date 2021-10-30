package me.jadjer.ansem.data.repository

import me.jadjer.ansem.data.model.entity.Request

interface RequestRepository {
    suspend fun getAll() : List<Request>
}