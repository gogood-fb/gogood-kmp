package org.gogood.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class CountriesDTO(
    val data: CountriesListDTO? = null,
) {
    @Serializable
    data class CountriesListDTO(
        val total: Int? = null,
        val index: List<CountryDTO>? = null,
    )

    @Serializable
    data class CountryDTO(
        val id: String? = null,
        val name: String? = null,
    )
}
