package org.gogood.data.repository

import org.koin.dsl.module

val repositoryModule = module {

    single<EntryRepository> {
        EntryDataRepository(get())
    }
}