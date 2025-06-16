package org.gogood.data.repository

import kotlinx.coroutines.flow.Flow
import org.gogood.data.DataResource
import org.gogood.data.dto.ElebaseDataWrapperDTO
import org.gogood.data.dto.EntryDTO

interface EntryRepository {

    fun getAnySingleEntry(): Flow<DataResource<ElebaseDataWrapperDTO<EntryDTO>>>

}