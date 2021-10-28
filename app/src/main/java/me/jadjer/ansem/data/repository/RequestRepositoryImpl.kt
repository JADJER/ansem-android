package me.jadjer.ansem.data.repository

import me.jadjer.ansem.data.api.RequestApi
import me.jadjer.ansem.data.model.entity.Request

class RequestRepositoryImpl(private val requestApi: RequestApi) : RequestRepository {

    override suspend fun getAll(): List<Request> {
        requestApi.getAll()

        return emptyList()
    }
}