package me.jadjer.ansem.app.di

import me.jadjer.ansem.data.repository.*
import org.koin.dsl.module

val repositoryModule = module {
    single<AccountRepository> { AccountRepositoryImpl(get()) }
    single<RequestRepository> { RequestRepositoryImpl(get(), get()) }
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
}