package org.gogood.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class GeoDTO(
    val id: String? = null,
    val dimensions: Int? = null,
    val element: String? = null, //Id of the associated Element
    val entry: String? = null, //Id of the associated Entry
    val features: List<GeoFeatureDTO>? = null,
    val feature: GeoFeatureDTO? = null,
    val json: String? = null,
    val point: String? = null,
    val zoom: Int? = null,
)
