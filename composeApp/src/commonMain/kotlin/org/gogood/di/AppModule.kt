package org.gogood.di

import kotlinx.coroutines.flow.MutableStateFlow
import org.gogood.AppState
import org.gogood.navigation.navigator.AppNavigator
import org.koin.dsl.module

val AppModule =
    module {
        single { AppNavigator() }
        single { MutableStateFlow(AppState()) }
    }
