package me.jadjer.ansem.app.di

import me.jadjer.ansem.data.repository.*
import org.koin.dsl.module
import org.koin.dsl.single

val repositoryModule = module {
    single<AccountRepository> { AccountRepositoryImpl(get()) }
    single<RequestRepository> { RequestRepositoryImpl(get(), get()) }
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    single<SessionRepository>{ SessionRepositoryImpl(get()) }
}