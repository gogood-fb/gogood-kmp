package org.gogood.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ElebaseDataWrapperDTO<T>(
    val data: ElebaseSubDataWrapperDTO<T>,
) {
    @Serializable
    data class ElebaseSubDataWrapperDTO<T>(
        val total: Int,
        val index: List<T>,
    )
}