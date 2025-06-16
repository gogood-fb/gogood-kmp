package org.gogood.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EmbedDTO(
    val id: String,
    @SerialName("date_time")
    val dateTime: DateTimeDTO? = null,
    val description: String? = null,
    val editable: Boolean? = null,
    @SerialName("ip_attribution")
    val ipAttribution: String? = null,
    val locale: String? = null,
    val title: String? = null,
    val url: String? = null,
    val user: UserIdDTO? = null,
)
