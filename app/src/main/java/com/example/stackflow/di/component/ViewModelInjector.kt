package com.example.stackflow.di.component

import com.example.stackflow.base.BaseViewModel
import com.example.stackflow.di.module.NetworkProvider
import com.example.stackflow.ui.MainActivityViewModel
import com.example.stackflow.ui.QuestionViewModel
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [(NetworkProvider::class)])
interface ViewModelInjector {

    fun inject(mainActivityViewModel: MainActivityViewModel)

    fun inject(questionViewModel: QuestionViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkProvider(networkProvider: NetworkProvider): Builder
    }
}