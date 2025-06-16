package org.gogood.di

import co.touchlab.kermit.Logger
import co.touchlab.kermit.loggerConfigInit
import org.koin.dsl.module

val loggerModule = module {
    single {
        Logger(loggerConfigInit())
    }
}