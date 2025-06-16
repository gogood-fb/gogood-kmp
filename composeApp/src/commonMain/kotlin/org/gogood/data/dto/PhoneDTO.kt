package org.gogood.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhoneDTO(
    val id: String,
    val country: String? = null,
    @SerialName("date_time")
    val dateTime: DateTimeDTO? = null,
    val extension: String? = null,
    val name: String? = null,
    val numberDTO: PhoneNumberDTO? = null,
    val user: String? = null, //TODO
    ) {
    @Serializable
    data class PhoneNumberDTO(
        val text: String? = null,
        //TODO, add in more data points if needed
    )
}