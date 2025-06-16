package org.gogood.data.datasource.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.gogood.data.DataResource
import org.gogood.data.api.entry.EntryAPI
import org.gogood.data.datasource.EntryDataSource
import org.gogood.data.dto.ElebaseDataWrapperDTO
import org.gogood.data.dto.EntryDTO

class EntryApiDataSource(
    private val entryAPI: EntryAPI,
) : EntryDataSource {
    override fun getAnySingleEntry(): Flow<DataResource<ElebaseDataWrapperDTO<EntryDTO>>> = flow {
        emit(DataResource.Loading)
        emit(entryAPI.getEntry())
    }
}