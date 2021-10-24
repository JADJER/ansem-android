package me.jadjer.ansem.app.di

import me.jadjer.ansem.fragments.login.LoginViewModel
import me.jadjer.ansem.fragments.registration.RegistrationViewModel
import me.jadjer.ansem.fragments.request_list.RequestListViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val viewModelModule = module {
    viewModel { RequestListViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { RegistrationViewModel(get()) }
}