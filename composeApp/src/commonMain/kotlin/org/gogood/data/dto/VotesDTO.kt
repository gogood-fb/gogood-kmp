package org.gogood.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class VotesDTO(
    val average: Float,
    val total: Int,
    val quantity: Int,
)
