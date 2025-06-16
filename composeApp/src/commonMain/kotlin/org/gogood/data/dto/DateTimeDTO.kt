package org.gogood.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class DateTimeDTO(
    val created:String,
    val edited:String,
)
