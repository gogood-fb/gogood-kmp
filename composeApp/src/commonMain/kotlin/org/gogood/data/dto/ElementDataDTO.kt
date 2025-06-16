package org.gogood.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ElementDataDTO(
    val boolean: Boolean? = null,
    val contact: List<ContactDTO>? = null,
    @SerialName("date_time")
    val dateTime: List<List<String>>? = null,
    val embed: List<EmbedDTO>? = null,
    val entry: List<EntryDTO>? = null,
    val file: List<FileDTO>? = null,
    val geo: GeoDTO? = null,
    val id: String? = null,
    val locations:List<LocationDTO>? = null,
    val number: Double? = null,
    val option: List<ElementOptionDTO>? = null,
    val phone: List<PhoneDTO>? = null,
    val text: String? = null,
)
