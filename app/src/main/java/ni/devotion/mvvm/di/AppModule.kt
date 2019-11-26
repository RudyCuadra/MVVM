package ni.devotion.mvvm.di

import ni.devotion.mvvm.data.network.remoteDataSourceModule
import ni.devotion.mvvm.repo.BusinessRepository
import ni.devotion.mvvm.repo.BusinessRepositoryImpl
import ni.devotion.mvvm.viewModel.BusinessViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { BusinessViewModel(get()) }

    single<BusinessRepository> {
        BusinessRepositoryImpl(get())
    }
}

val allAppModules = listOf(appModule, remoteDataSourceModule)