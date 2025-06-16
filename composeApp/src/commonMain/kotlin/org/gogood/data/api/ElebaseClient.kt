package org.gogood.data.api

import co.touchlab.kermit.Logger
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.headers
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.encodeBase64
import kotlinx.serialization.json.Json
import org.gogood.data.DataResource
import org.gogood.data.DataStoreConfig

class ElebaseClient(
    private val config: DataStoreConfig,
    private val appLogger: Logger,
) {

    private val client = HttpClient {
        expectSuccess = true

        defaultRequest {
            url(config.eleBaseUrl)
            contentType(ContentType.Application.Json)
        }

        install(ContentNegotiation) {
            json(
                Json {
                    isLenient = true
                },
            )
        }

        install(Logging) {
            logger = object : io.ktor.client.plugins.logging.Logger {
                override fun log(message: String) {
                    appLogger.i { message }
                }
            }
            level = LogLevel.BODY
        }
    }

    suspend inline fun <reified T> makeRequest(
        httpMethod: HttpMethod,
        noinline block: HttpRequestBuilder.() -> Unit,
    ): DataResource<T> {
        return try {
            val result = rawRequest(httpMethod, block)
            DataResource.Success(result.body())
        } catch (e: Exception) {
            DataResource.Error(e)
        }
    }

    suspend fun rawRequest(
        httpMethod: HttpMethod,
        block: HttpRequestBuilder.() -> Unit,
    ): HttpResponse {
        val builder = HttpRequestBuilder()
            .apply {
                method = httpMethod
                headers {
                    headers {
                        append(HttpHeaders.Authorization, getAuthHeader())
                    }
                }
            }.apply(block)

        return client.request(builder)
    }

    private fun getAuthHeader() = "Basic " + "$USER_ID:$API_TOKEN".encodeBase64()

    companion object {
        private const val API_TOKEN =
            "1ad412716323073302ba12a2beaca73af3a5ffd97b335ef00f46a5eee9405db5"
        private const val USER_ID = "71f2f48c-4084-4367-a11d-dc897d4bdd7b"
    }
}