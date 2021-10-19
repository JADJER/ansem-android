package me.jadjer.ansem.data.api

import me.jadjer.ansem.data.model.api.Auth
import me.jadjer.ansem.data.model.api.Registration
import me.jadjer.ansem.utils.ResponseWrapper
import retrofit2.http.*

interface AuthApi {

    @Headers("Accept: application/json")
    @POST("api/register")
    @FormUrlEncoded
    suspend fun register(
        @Field("name") name: String,
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("c_password") password_again: String,
    ): ResponseWrapper<Registration>

    @Headers("Accept: application/json")
    @POST("oauth/token")
    @FormUrlEncoded
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("grant_type") grant_type: String = "password",
        @Field("client_id") client_id: Int = 2,
        @Field("client_secret") client_secret: String = "N6lyLm2LYYhT4vFAqUlUh6XrabLpaSCGKUULiet8"
    ): Auth
}