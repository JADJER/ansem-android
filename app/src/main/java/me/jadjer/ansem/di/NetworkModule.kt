package me.jadjer.ansem.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.tls.HandshakeCertificates
import okhttp3.tls.decodeCertificatePem
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val netModule = module {
    single<OkHttpClient> { provideSslOkhttpClient() }
    single<Retrofit> { provideRetrofit(get<OkHttpClient>()) }
}

fun provideDefaultOkhttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

    return OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()
}

fun provideSslOkhttpClient(): OkHttpClient {
    val certificateAuthority = """
    -----BEGIN CERTIFICATE-----
    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
    LQRME7CL/Nwwy7tOIDLx1f2bjqaN
    -----END CERTIFICATE-----
    """.trimIndent().decodeCertificatePem()

    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

    val certificates = HandshakeCertificates.Builder()
        .addTrustedCertificate(certificateAuthority)
        .build()

    return OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .sslSocketFactory(certificates.sslSocketFactory(), certificates.trustManager)
        .build()
}

fun provideRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://motoecu.jadjer.me/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
}