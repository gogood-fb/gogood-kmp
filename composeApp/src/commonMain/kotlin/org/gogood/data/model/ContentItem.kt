package org.gogood.data.model

import gogood.composeapp.generated.resources.Res
import gogood.composeapp.generated.resources.category_culture
import gogood.composeapp.generated.resources.category_dining
import gogood.composeapp.generated.resources.category_events
import gogood.composeapp.generated.resources.category_experts
import gogood.composeapp.generated.resources.category_lodging
import gogood.composeapp.generated.resources.category_outdoor
import gogood.composeapp.generated.resources.category_support
import gogood.composeapp.generated.resources.category_sustainability
import gogood.composeapp.generated.resources.ico_culture
import gogood.composeapp.generated.resources.ico_dining
import gogood.composeapp.generated.resources.ico_event
import gogood.composeapp.generated.resources.ico_experts
import gogood.composeapp.generated.resources.ico_lodging
import gogood.composeapp.generated.resources.ico_outdoor
import gogood.composeapp.generated.resources.ico_support
import gogood.composeapp.generated.resources.ico_sustainability
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

data class ContentItem(
    val id: String,
    val destinationId: String,
    val type: Type,
    val title: String,
    val subtitle: String,
    val tileUrlSmall: String,
    val tileUrlMedium: String,
    val additionalImageUrls: List<String>,
    val location: Location,
    val contentMarkdown: String,
    val sustainabilityScore: Int,
    val phoneNumber: String?,
    val website: String?,
) : Tileable {
    fun getAddress(): String {
        return "23 Saint Matthew Street\nBelize City, Belize"
    }

    override fun toTile() =
        TileData(
            title = title,
            subtitle = subtitle,
            description = "",
            imageUrlLarge = tileUrlMedium,
            imageUrlSmall = tileUrlSmall,
            additionalImages = additionalImageUrls,
            sustainabilityScore = sustainabilityScore,
            actions =
                buildList {
                    if (phoneNumber != null) {
                        add(Action.Call(phoneNumber))
                    }

                    if (website != null) {
                        add(Action.Website(website))
                    }

                    add(Action.Share(title))
                    add(Action.AddToTrip)
                    add(Action.AddToFavorites)
                    add(Action.Directions(location))
                },
        )

    enum class Type(
        val iconRes: DrawableResource,
        val labelRes: StringResource,
    ) {
        CULTURE(
            Res.drawable.ico_culture,
            Res.string.category_culture,
        ),
        OUTDOOR(
            Res.drawable.ico_outdoor,
            Res.string.category_outdoor,
        ),
        EVENT(
            Res.drawable.ico_event,
            Res.string.category_events,
        ),
        DINING(
            Res.drawable.ico_dining,
            Res.string.category_dining,
        ),
        LODGING(
            Res.drawable.ico_lodging,
            Res.string.category_lodging,
        ),
        SUSTAINABILITY(
            Res.drawable.ico_sustainability,
            Res.string.category_sustainability,
        ),
        EXPERTS(
            Res.drawable.ico_experts,
            Res.string.category_experts,
        ),
        SUPPORT(
            Res.drawable.ico_support,
            Res.string.category_support,
        ),
    }
}
