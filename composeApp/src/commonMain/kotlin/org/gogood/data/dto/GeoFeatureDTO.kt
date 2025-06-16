package org.gogood.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeoFeatureDTO(
    val id: String? = null,
    val attributes: GeoFeatureAttributesDTO? = null, //TODO,
    @SerialName("date_time")
    val dateTime: DateTimeDTO? = null,
    val json: String? = null,
    val ordinal: Int? = null,
    val parent: String? = null,//TODO
    val text: String? = null,
    val type: GeoFeatureTypeDTO? = null,
    val types: List<GeoFeatureTypeDTO>? = null,
    val user: UserIdDTO? = null, //TODO
) {
    @Serializable
    data class GeoFeatureAttributesDTO(
        @SerialName("ID")
        val id: Int? = null,
        @SerialName("FID")
        val fid: Int? = null,
        @SerialName("AREA")
        val area: Double? = null,
        @SerialName("ACRES")
        val acres: Double? = null,
        @SerialName("PROJECT")
        val project: String? = null,
        @SerialName("BOUNDARY")
        val boundary: String? = null,
        @SerialName("HECTARES")
        val hectares: Double? = null,
        @SerialName("PERIMETER")
        val perimeter: Double? = null,
    )
}
