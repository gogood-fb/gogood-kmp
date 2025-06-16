package org.gogood.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TypeDTO<T : TypeDTO.TypeGroupInterface>(
    val id: String,
    val attributes: String? = null,//TODO
    val contacts: Boolean? = null,
    @SerialName("date_time")
    val dateTime: DateTimeDTO? = null,
    val depth: Int? = null,
    val description: String? = null,
    val elements: List<ElementsDTO>? = null,
    val enabled: Boolean? = null,
    val groups: T? = null,
    val ordinal: Int? = null,
    @SerialName("include_linked_entries")
    val includeLinkedEntries: Boolean? = null,
    val name: String? = null,
    val parents: List<String>? = null, //TODO,
    val types: List<TypeDTO<T>>? = null,
) {
    interface TypeGroupInterface

    @Serializable
    data class TypeGroupDTO(
        val type: List<GroupDTO>?,
        val user: List<GroupDTO>?,
    ) : TypeGroupInterface

    @Serializable
    data class TypeGroupIdsDTO(
        val type: List<String>,
        val user: List<String>,
    ) : TypeGroupInterface
}
