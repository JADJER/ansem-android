package me.jadjer.ansem.utils

import android.util.Log
import me.jadjer.ansem.data.repository.AccountRepository
import me.jadjer.ansem.data.repository.AuthRepository
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.koin.java.KoinJavaComponent.inject
import kotlin.Lazy


class AuthorizationInterceptor : Interceptor {

    private val accountRepository: Lazy<AccountRepository> =
        inject(AccountRepository::class.java)

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().signedRequest()
        return chain.proceed(newRequest)
    }

    private fun Request.signedRequest(): Request {
        val builder = newBuilder()
        builder.header("x-api-key", "qGQWJ0UcTg8Q7dVdKzGNuNhDw80--55rOw4Uuw6H3EY")

        val token = accountRepository.value.getToken()
        if (token != null) {
            builder.header("Authorization", "Bearer $token")
        }

        return builder.build()
    }
}