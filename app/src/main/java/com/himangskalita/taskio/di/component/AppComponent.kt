package com.himangskalita.taskio.di.component

import android.content.Context
import androidx.lifecycle.ViewModel
import com.himangskalita.taskio.fragment.HomeScreenFragment
import com.himangskalita.taskio.di.component.module.TaskRepositoryModule
import com.himangskalita.taskio.di.component.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        TaskRepositoryModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {

    fun inject(homeScreenFragment: HomeScreenFragment)

    fun getMap() : Map<Class<*>, ViewModel>

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context) : AppComponent
    }
}