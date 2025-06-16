package org.gogood

import org.gogood.data.model.ContentItem
import org.gogood.data.model.Destination
import org.gogood.data.model.FavoriteList
import org.gogood.data.model.Tileable
import org.gogood.data.model.Trip
import org.gogood.ui.PreviewData

data class AppState(
    val destinations: List<Destination> = PreviewData.destinations,
    val contentItems: List<ContentItem> = PreviewData.contentItems,
    val trips: List<Trip> = PreviewData.trips,
    val favorites: List<FavoriteList> = PreviewData.favorites,
    val selectedDestination: Destination? = null,
    val selectedTrip: Trip? = null,
    val selectedFavoriteList: FavoriteList? = null,
    val selectedGalleryData: Tileable? = null,
    val selectedContentItemExplore: ContentItem? = null,
    val selectedContentItemTrips: ContentItem? = null,
    val selectedContentItemFavorites: ContentItem? = null,
)
