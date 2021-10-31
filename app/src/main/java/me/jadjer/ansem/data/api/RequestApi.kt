package me.jadjer.ansem.data.api

import me.jadjer.ansem.data.model.api.RequestResponse
import me.jadjer.ansem.utils.ResponseWrapper
import retrofit2.http.*

interface RequestApi {

    @GET("profile/requests")
    suspend fun getAll(): ResponseWrapper<List<RequestResponse>>
}