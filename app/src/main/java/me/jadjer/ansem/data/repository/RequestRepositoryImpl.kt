package me.jadjer.ansem.data.repository

import me.jadjer.ansem.data.api.RequestApi
import me.jadjer.ansem.data.model.api.Request
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
        for (request in requests) {
            requestDao.updateRequest(
                RequestEntity(
                    request.id,
                    request.school,
                    request.class_no,
                    request.score,
                    request.index,
                    request.session_id
                )
            )
        }

        return requestDao.getAllRequests()
    }

    override suspend fun remove(requestId: Int): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun create(
        school: String,
        classNo: String,
        score: Double,
        index: Int,
        sessionId: Int
    ): Boolean {
        val request = requestApi.create(
            Request(
                school = school,
                class_no = classNo,
                score = score,
                index = index,
                session_id = sessionId
            )
        )
        return request.success
    }
}