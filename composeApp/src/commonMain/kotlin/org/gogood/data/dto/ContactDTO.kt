package org.gogood.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContactDTO(
    val id: String,
    @SerialName("date_time")
    val dateTime: DateTimeDTO? = null,
    val email: String? = null,
    val groups: String? = null, //TODO
    val locations: List<LocationDTO>? = null,
    val name: ContactNameDTO? = null,
    val organization: String? = null,
    val phones: List<PhoneDTO>? = null,
    val url: String? = null,
    val user: String? = null, //TODO

) {
    @Serializable
    data class ContactNameDTO(
        val first: String? = null,
        val last: String? = null,
    )
}