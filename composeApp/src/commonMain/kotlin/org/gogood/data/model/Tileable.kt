package org.gogood.data.model

interface Tileable {
    fun toTile(): TileData
}

data class TileData(
    val title: String,
    val subtitle: String,
    val description: String?,
    val imageUrlLarge: String,
    val imageUrlSmall: String,
    val sustainabilityScore: Int,
    val additionalImages: List<String>,
    val actions: List<Action>,
)
