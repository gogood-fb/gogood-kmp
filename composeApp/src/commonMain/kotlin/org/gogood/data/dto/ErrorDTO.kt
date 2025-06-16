package org.gogood.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ErrorDTO(
    val id:String,
    val data:String? = null,
)