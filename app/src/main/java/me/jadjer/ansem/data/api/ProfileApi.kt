package me.jadjer.ansem.data.api

import me.jadjer.ansem.data.model.api.Profile
import me.jadjer.ansem.utils.ResponseWrapper
import retrofit2.http.*

interface ProfileApi {

    @POST("users")
    suspend fun create(@Body profile: Profile): ResponseWrapper<Profile>
}