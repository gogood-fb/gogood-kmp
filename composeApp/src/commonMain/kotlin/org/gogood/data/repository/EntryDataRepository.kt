package org.gogood.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import org.gogood.data.DataResource
import org.gogood.data.datasource.EntryDataSource
import org.gogood.data.dto.ElebaseDataWrapperDTO
import org.gogood.data.dto.EntryDTO

class EntryDataRepository(
    private val entryDataSource: EntryDataSource,
) : EntryRepository {
    override fun getAnySingleEntry(): Flow<DataResource<ElebaseDataWrapperDTO<EntryDTO>>> =
        entryDataSource.getAnySingleEntry().flowOn(Dispatchers.Default)

}