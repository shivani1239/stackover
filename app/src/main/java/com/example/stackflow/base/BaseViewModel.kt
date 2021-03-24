package com.example.stackflow.base

import androidx.lifecycle.ViewModel
import com.example.stackflow.di.component.DaggerViewModelInjector
import com.example.stackflow.di.component.ViewModelInjector
import com.example.stackflow.di.module.NetworkProvider
import com.example.stackflow.ui.MainActivityViewModel
import com.example.stackflow.ui.QuestionViewModel

abstract class BaseViewModel : ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkProvider(NetworkProvider)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is MainActivityViewModel -> injector.inject(this)
            is QuestionViewModel ->injector.inject(this)
        }
    }

}