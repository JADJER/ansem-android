package me.jadjer.ansem.di

import me.jadjer.ansem.data.repository.*
import org.koin.dsl.module

val repositoryModule = module {
    single<MotorcycleRepository> { MotorcycleRepositoryImpl() }
    single<AccountRepository> { AccountRepositoryImpl(get()) }
    single<DeviceAssistantRepository> { DeviceAssistantRepositoryImpl() }
}