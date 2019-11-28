package ni.devotion.mvvm.di

import ni.devotion.mvvm.data.network.remoteDataSourceModule
import ni.devotion.mvvm.repo.BusinessRepository
import ni.devotion.mvvm.repo.BusinessRepositoryImpl
import ni.devotion.mvvm.repo.CategoriesRepository
import ni.devotion.mvvm.repo.CategoriesRepositoryImpl
import ni.devotion.mvvm.viewModel.BusinessViewModel
import ni.devotion.mvvm.viewModel.CategoriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        BusinessViewModel(get())
    }

    viewModel {
        CategoriesViewModel(get())
    }

    single<BusinessRepository> {
        BusinessRepositoryImpl(get())
    }

    single<CategoriesRepository> {
        CategoriesRepositoryImpl(get())
    }



}

val allAppModules = listOf(appModule, remoteDataSourceModule)