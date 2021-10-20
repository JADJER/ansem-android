package me.jadjer.ansem.di

import me.jadjer.ansem.ui.login.LoginViewModel
import me.jadjer.ansem.ui.request_list.RequestListViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val viewModelModule = module {
    viewModel { RequestListViewModel(get()) }
    viewModel { LoginViewModel(get()) }
//    viewModel { UserProfileViewModel(get()) }
}