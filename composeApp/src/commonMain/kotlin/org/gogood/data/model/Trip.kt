package org.gogood.data.model

data class Trip(
    val id: String,
    val name: String,
    val description: String,
    val sustainabilityScore: Int,
    // Int represents ordinal for sorting
    val items: Map<ContentItem, Int>,
) : Tileable {
    fun getItemsOrdered() = items.entries.sortedBy { it.value }.map { it.key }

    override fun toTile() =
        TileData(
            title = name,
            subtitle = "",
            description = description,
            imageUrlLarge = getItemsOrdered().first().tileUrlMedium,
            imageUrlSmall = getItemsOrdered().first().tileUrlSmall,
            additionalImages = emptyList(),
            sustainabilityScore = sustainabilityScore,
            actions =
                listOf(
                    Action.Share(name),
                ),
        )
}
