package com.himangskalita.taskio.di.component.module

import androidx.lifecycle.ViewModel
import com.himangskalita.taskio.factory.HomeScreenViewModelFactory
import com.himangskalita.taskio.viewmodel.HomeScreenFragmentViewmodel
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @ClassKey(HomeScreenFragmentViewmodel::class)
    @IntoMap
    abstract fun bindHomeScreenViewModelFactory(homeScreenViewModelFactory: HomeScreenFragmentViewmodel) : ViewModel
}