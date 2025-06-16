package org.gogood.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EntryDTO(
    val id: String,
    @SerialName("date_time")
    val dateTime: DateTimeDTO? = null,
    val distance: Float? = null,
    val editable: Boolean? = null,
    val elements: List<ElementsDTO>? = null,
    val groups: List<GroupsDTO>? = null,
    val contacts: List<ContactDTO>? = null,
    val pending: String? = null,
    val phase: String? = null,
    val published: String? = null,
    val title: String? = null,
    val types: List<TypeDTO<TypeDTO.TypeGroupIdsDTO>>? = null,
    val users: List<String>? = null,
    val votes: VotesDTO? = null,
)
