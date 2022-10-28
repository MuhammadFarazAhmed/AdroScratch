package com.example.sharedcode.domain.di

import com.example.sharedcode.data.repo.HomeRepository
import com.example.sharedcode.data.repo.HomeRepositoryImp
import com.example.sharedcode.domain.usecase.HomeUseCase
import com.example.sharedcode.domain.usecase.HomeUseCaseImp
import com.example.sharedcode.platformModule
import com.example.sharedcode.presentation.HomeViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module


fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(commonModule())
}

// called by iOS etc
fun initKoin(baseUrl: String) = initKoin {}

fun commonModule() = platformModule() + useCaseModule() + dataModule() + viewModelModule()


fun dataModule() = module {
    
    single<HomeRepository> { HomeRepositoryImp(get()) }
    
}

fun useCaseModule() = module {
    single<HomeUseCase> { HomeUseCaseImp(get()) }
}

fun viewModelModule() = module {
    single { HomeViewModel(get()) }
}





