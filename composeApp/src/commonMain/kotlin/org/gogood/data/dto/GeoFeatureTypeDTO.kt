package org.gogood.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeoFeatureTypeDTO(
    val id: String,
    val attributes: String? = null, //TODO
    val color: String? = null,
    @SerialName("date_time")
    val dateTime: DateTimeDTO? = null,
    val default: Boolean? = null,
    val enabled: Boolean? = null,
    val name: String? = null,
    val quantity: List<Int>? = null, //TODO
    val type: String? = null, //This will be an enum
)