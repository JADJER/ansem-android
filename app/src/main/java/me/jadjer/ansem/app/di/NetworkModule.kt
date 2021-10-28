package me.jadjer.ansem.app.di

import me.jadjer.ansem.utils.AuthorizationInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
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

fun provideSslOkhttpClient(): OkHttpClient {
    val certificateAuthority = """
    -----BEGIN CERTIFICATE-----
    MIIFzDCCBLSgAwIBAgIRAP5c/57m/SeixTrlafOyp+4wDQYJKoZIhvcNAQELBQAw
    gY8xCzAJBgNVBAYTAkdCMRswGQYDVQQIExJHcmVhdGVyIE1hbmNoZXN0ZXIxEDAO
    BgNVBAcTB1NhbGZvcmQxGDAWBgNVBAoTD1NlY3RpZ28gTGltaXRlZDE3MDUGA1UE
    AxMuU2VjdGlnbyBSU0EgRG9tYWluIFZhbGlkYXRpb24gU2VjdXJlIFNlcnZlciBD
    QTAeFw0yMTAyMTYwMDAwMDBaFw0yMjAzMTgyMzU5NTlaMB8xHTAbBgNVBAMMFCou
    cHl0aG9uYW55d2hlcmUuY29tMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKC
    AQEAyHGZn89ckRHC7htmk1+O8/pzuafbf18Km8h4J/dIATZFfARJtmLfkFyTMHkW
    yFt6cRBd+A4oCYRjkGyG/BGckm1FsGZd7CVNJpVqpKImilpBzS6fB9fo6FiraBp9
    zVEFJhCBGY8ocYZiOzCOLNgE5/oTyMvEyEq3Jn01Ao7T+3W/LpupCXtU9Bx/ELEg
    A9y47FH4ny3a8LlG2P/jA70VjJR72hiEjgbdh/gVKXXa8WlwujoQH2gryqBQMtFr
    j7NEXAChMVIbgV6V2ZGqdVNUt3+694uaTOjCt8tl3Svx5WY50P7gYTeIge7NUG9i
    0WD7iAcW9AEcGlgVPmrPxhu3jQIDAQABo4ICkDCCAowwHwYDVR0jBBgwFoAUjYxe
    xFStiuF36Zv5mwXhuAGNYeEwHQYDVR0OBBYEFGoCFCjk9zSrajmmQ+6Pyg1e38XU
    MA4GA1UdDwEB/wQEAwIFoDAMBgNVHRMBAf8EAjAAMB0GA1UdJQQWMBQGCCsGAQUF
    BwMBBggrBgEFBQcDAjBJBgNVHSAEQjBAMDQGCysGAQQBsjEBAgIHMCUwIwYIKwYB
    BQUHAgEWF2h0dHBzOi8vc2VjdGlnby5jb20vQ1BTMAgGBmeBDAECATCBhAYIKwYB
    BQUHAQEEeDB2ME8GCCsGAQUFBzAChkNodHRwOi8vY3J0LnNlY3RpZ28uY29tL1Nl
    Y3RpZ29SU0FEb21haW5WYWxpZGF0aW9uU2VjdXJlU2VydmVyQ0EuY3J0MCMGCCsG
    AQUFBzABhhdodHRwOi8vb2NzcC5zZWN0aWdvLmNvbTAzBgNVHREELDAqghQqLnB5
    dGhvbmFueXdoZXJlLmNvbYIScHl0aG9uYW55d2hlcmUuY29tMIIBBAYKKwYBBAHW
    eQIEAgSB9QSB8gDwAHYARqVV63X6kSAwtaKJafTzfREsQXS+/Um4havy/HD+bUcA
    AAF3qtgEAQAABAMARzBFAiAfL16kk7zGibGBrUnFTyjxyIZdJzonIXgataCBUAKb
    YwIhAJjQxt0g1spronXS220Rr0rjgpsPW7qA4FmReJA0juSTAHYA36Veq2iCTx9s
    re64X04+WurNohKkal6OOxLAIERcKnMAAAF3qtgELwAABAMARzBFAiEAjj4at/QH
    tGg3pt9ReNmMEpD82sIgA/+SE+LaDjoyrwoCIHG9Gjk2/EJE8KKVFDyX8KfGOauL
    T3+FlrDp8fkhAtt0MA0GCSqGSIb3DQEBCwUAA4IBAQCyrhXoZ7WIJDM7aZcYg9pu
    mfD3GbO64efrNQqS2sXJnOPit73W1+1nzgipMQxLNCV0NL7RdMvld+uEEuHbj6Kb
    Cz7BgUxpvDWiNy+xBXZcbI4K107qqqDvnCL9Y0CXZXZhLWP103gT2aAhnA8DeuoL
    YuiQNk+vg8UG0X+h34TdjKoTX3ydTFNLhyl9ivGNoMXigafn8xJco1Ud7kplXa4w
    1qEO2TnV05erbnqALtF+lU0MTO1Jfe+5u0a/k+D8nN8OTfU22Jxffy2+CRyZ94RO
    cU1betQ0CvnoHVugDhJsRqb/Ju+yBA0N/cnYxQOFfpEZqkOH5QsbO51km9Qw+aEn
    -----END CERTIFICATE-----
    """.trimIndent().decodeCertificatePem()

    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

    val certificates = HandshakeCertificates.Builder()
        .addTrustedCertificate(certificateAuthority)
        .build()

    val httpClient = OkHttpClient.Builder()
        .addInterceptor(AuthorizationInterceptor())
        .addInterceptor(interceptor)
        .sslSocketFactory(certificates.sslSocketFactory(), certificates.trustManager)

    return httpClient.build()
}

fun provideRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://jadjer.pythonanywhere.com/api/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
}