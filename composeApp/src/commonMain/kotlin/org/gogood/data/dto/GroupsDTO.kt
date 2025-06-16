package org.gogood.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class GroupsDTO(
    val element: List<String>? = null,
    val user: List<String>? = null,
)