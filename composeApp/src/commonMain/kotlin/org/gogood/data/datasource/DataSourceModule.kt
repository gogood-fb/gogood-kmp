package org.gogood.data.datasource

import org.gogood.data.datasource.remote.EntryApiDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single<EntryDataSource> {
        EntryApiDataSource(get())
    }
}