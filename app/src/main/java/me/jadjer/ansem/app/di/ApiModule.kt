package me.jadjer.ansem.app.di

import me.jadjer.ansem.data.api.*
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single { provideAuthApiService(get()) }
    single { provideProfileApiService(get()) }
    single { provideRequestApiService(get()) }
    single { provideSessionApiService(get()) }
}

fun provideAuthApiService(retrofit: Retrofit): AuthApi {
    return retrofit.create(AuthApi::class.java)
}

fun provideProfileApiService(retrofit: Retrofit): ProfileApi {
    return retrofit.create(ProfileApi::class.java)
}

fun provideRequestApiService(retrofit: Retrofit): RequestApi {
    return retrofit.create(RequestApi::class.java)
}

fun provideSessionApiService(retrofit: Retrofit): SessionApi {
    return retrofit.create(SessionApi::class.java)
}