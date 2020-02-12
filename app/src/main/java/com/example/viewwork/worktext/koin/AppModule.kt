package com.example.viewwork.worktext.koin

import com.example.viewwork.worktext.repository.GitRepository
import com.example.viewwork.worktext.ui.MainViewModel
import com.example.viewwork.worktext.ui.user_data.UserDataViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { UserDataViewModel(get()) }
}

val repositoryModule = module {
    single { GitRepository(get()) }
}


