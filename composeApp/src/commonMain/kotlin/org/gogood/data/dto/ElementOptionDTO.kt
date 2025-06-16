package org.gogood.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ElementOptionDTO(
    val id: String,
    val element: ElementsDTO? = null,
    val options: List<ElementOptionDTO>? = null,
    val ordinal: Int? = null,
    val parent: ElementOptionDTO? = null,
    val text: String? = null,
)
