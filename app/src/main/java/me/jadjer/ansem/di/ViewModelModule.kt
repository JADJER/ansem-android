package me.jadjer.ansem.di

import me.jadjer.ansem.ui.splash.SplashViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
}