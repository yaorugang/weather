package com.yaorugang.weather.injection.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yaorugang.weather.injection.ViewModelFactory
import com.yaorugang.weather.ui.MainActivity
import com.yaorugang.weather.ui.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class UiModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}