package me.jadjer.ansem.data.repository

import me.jadjer.ansem.data.api.RequestApi
import me.jadjer.ansem.data.model.dao.RequestDao
import me.jadjer.ansem.data.model.entity.RequestEntity

class RequestRepositoryImpl(
    private val requestApi: RequestApi,
    private val requestDao: RequestDao
) : RequestRepository {

    override suspend fun getAll(): List<RequestEntity> {
        val response = requestApi.getAll()
        if (!response.success) {
            return emptyList()
        }

        val requests = response.data!!
        for (requestAPI in requests) {
            requestDao.updateRequest(
                RequestEntity(
                    requestAPI.id,
                    requestAPI.school,
                    requestAPI.class_no,
                    requestAPI.score,
                    requestAPI.index
                )
            )
        }

        return requestDao.getAllRequests()
    }

    override suspend fun remove(requestId: Int): Boolean {
        TODO("Not yet implemented")
    }
}