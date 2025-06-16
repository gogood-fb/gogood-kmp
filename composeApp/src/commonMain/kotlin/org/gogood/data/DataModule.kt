package org.gogood.data

import org.gogood.data.api.apiModule
import org.gogood.data.datasource.dataSourceModule
import org.gogood.data.repository.repositoryModule
import org.koin.dsl.module

val dataModule = module {
    includes(apiModule)
    includes(dataSourceModule)
    includes(repositoryModule)


    single {
        DataStoreConfig()
    }
}