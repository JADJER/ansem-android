package me.jadjer.ansem.data.api

import me.jadjer.ansem.data.model.api.Request
import me.jadjer.ansem.data.model.api.Session
import me.jadjer.ansem.utils.ResponseWrapper
import retrofit2.http.*

interface RequestApi {

    @GET("profile/requests")
    suspend fun getAll(): ResponseWrapper<List<Request>>

    @POST("profile/requests")
    suspend fun create(@Body request: Request): ResponseWrapper<Request>
}