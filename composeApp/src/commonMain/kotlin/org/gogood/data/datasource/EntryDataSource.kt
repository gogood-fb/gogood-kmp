package org.gogood.data.datasource

import kotlinx.coroutines.flow.Flow
import org.gogood.data.DataResource
import org.gogood.data.dto.ElebaseDataWrapperDTO
import org.gogood.data.dto.EntryDTO

interface EntryDataSource {
    fun getAnySingleEntry(): Flow<DataResource<ElebaseDataWrapperDTO<EntryDTO>>>
}