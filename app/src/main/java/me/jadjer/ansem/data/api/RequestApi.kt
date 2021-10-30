package me.jadjer.ansem.data.api

import me.jadjer.ansem.data.model.api.Request
import me.jadjer.ansem.utils.ResponseWrapper
import retrofit2.http.*

interface RequestApi {

    @GET("requests")
    suspend fun getAll(): ResponseWrapper<Request>
}