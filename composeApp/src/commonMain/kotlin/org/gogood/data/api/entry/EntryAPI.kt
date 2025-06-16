package org.gogood.data.api.entry

import io.ktor.http.HttpMethod
import io.ktor.http.path
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import org.gogood.data.DataResource
import org.gogood.data.api.ElebaseClient
import org.gogood.data.dto.ElebaseDataWrapperDTO
import org.gogood.data.dto.EntryDTO

class EntryAPI(
    private val elebaseClient: ElebaseClient,
) {

    suspend fun getEntry(): DataResource<ElebaseDataWrapperDTO<EntryDTO>> =
        withContext(Dispatchers.IO) {
            elebaseClient.makeRequest(HttpMethod.Get) {
                url {
                    path("entries")
                    parameters.append("phase", "4")
                    parameters.append("limit", "50")
                    parameters.append("type", "fe6c056c-43ec-46c3-b504-971fad6b2cd8")
                }
            }
        }
}