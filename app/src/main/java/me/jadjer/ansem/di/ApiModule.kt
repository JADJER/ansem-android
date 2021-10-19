package me.jadjer.ansem.di

import me.jadjer.ansem.data.api.AuthApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single { provideAuthApiService(get()) }
}

fun provideAuthApiService(retrofit: Retrofit): AuthApi {
    return retrofit.create(AuthApi::class.java)
}