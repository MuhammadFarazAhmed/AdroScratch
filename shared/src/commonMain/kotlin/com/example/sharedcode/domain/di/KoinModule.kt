package com.example.sharedcode.domain.di

import com.example.sharedcode.platformModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration


fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
        startKoin {
            appDeclaration()
            modules(commonModule())
        }

// called by iOS etc
fun initKoin(baseUrl: String) = initKoin {}

fun commonModule() = platformModule() //+ networkModule() + dataModule()

//fun networkModule() = module {
//
//    single { CLibController() }
//
//    single { ApisEncryptionUtils(get()) }
//
//    single {
//        createHttpClient(httpClientEngine = get(),
//                cLibController = get(),
//                token = get(),
//                encryptionUtils = get())
//    }
//}
//
//fun dataModule() = module {
//
//    single<HomeRepository> { HomeRepositoryImp(get()) }
//
//    single<HomeUseCase> { HomeUseCaseImp(get()) }
//
//}



