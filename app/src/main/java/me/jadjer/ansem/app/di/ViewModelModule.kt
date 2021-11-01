package me.jadjer.ansem.app.di

import me.jadjer.ansem.fragments.login.LoginViewModel
import me.jadjer.ansem.fragments.registration.RegistrationViewModel
import me.jadjer.ansem.fragments.request_create.RequestCreateViewModel
import me.jadjer.ansem.fragments.request_list.RequestListViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val viewModelModule = module {
    viewModel { RequestListViewModel(get(), get(), get()) }
    viewModel { LoginViewModel(get(), get(), get()) }
    viewModel { RegistrationViewModel(get()) }
    viewModel { RequestCreateViewModel(get()) }
}