package org.gogood.data.api

import org.gogood.data.api.entry.EntryAPI
import org.koin.dsl.module

val apiModule = module {

    //Clients
    single {
        ElebaseClient(get(), get())
    }

    //APIs
    single {
        EntryAPI(get())
    }
}