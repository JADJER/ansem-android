package me.jadjer.ansem.utils

import me.jadjer.ansem.data.repository.AuthRepository
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.koin.java.KoinJavaComponent.inject
import kotlin.Lazy


class AuthorizationInterceptor : Interceptor {

    private val authRepository: Lazy<AuthRepository> =
        inject(AuthRepository::class.java)

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().signedRequest()
        return chain.proceed(newRequest)
    }

    private fun Request.signedRequest(): Request {
        val builder = newBuilder()
        builder.header("x-api-key", "egHllmJBgBW0ygbQ4ixolDmTmGpF-vauRuGuyUuxQ8A")

        val hasToken = authRepository.value.isAuth()
        if (hasToken) {
            builder.header("Authorization", "Bearer " + authRepository.value.getToken())
        }

        return builder.build()
    }
}