package me.jadjer.ansem.data.api

import me.jadjer.ansem.data.model.api.Session
import me.jadjer.ansem.utils.ResponseWrapper
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SessionApi {

    @GET("sessions/active")
    suspend fun getActive(): ResponseWrapper<List<Session>>

    @GET("sessions/all")
    suspend fun getAll(): ResponseWrapper<List<Session>>

    @GET("sessions/{session_id}")
    suspend fun get(@Path("session_id") session_id: Int): ResponseWrapper<Session>

    @POST("sessions")
    suspend fun create(@Body session: Session): ResponseWrapper<Session>
}