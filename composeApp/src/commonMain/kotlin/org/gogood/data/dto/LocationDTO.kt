package org.gogood.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationDTO(
    val id: String,
    val city: String? = null,
    val country: String? = null,
    @SerialName("date_time")
    val dateTime: DateTimeDTO? = null,
    val detail: String? = null,
    val division: LocationDivisionDTO? = null,
    val name: String? = null,
    val point: String? = null,
    val postCode: String? = null,
    val user: String? = null,
) {
    @Serializable
    data class LocationDivisionDTO(
        val code: String? = null,
        val test: String? = null,
    )
}
