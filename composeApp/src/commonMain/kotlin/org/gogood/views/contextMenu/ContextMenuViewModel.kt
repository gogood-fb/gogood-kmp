package org.gogood.views.contextMenu

import androidx.lifecycle.ViewModel
import co.touchlab.kermit.Logger
import kotlinx.coroutines.flow.MutableStateFlow
import org.gogood.AppState
import org.gogood.data.model.Action
import org.gogood.data.model.ContentItem
import org.gogood.data.model.FavoriteList
import org.gogood.data.model.Tileable
import org.gogood.data.model.Trip
import org.gogood.navigation.navigator.AppNavigator
import org.gogood.navigation.FavoritesRoutes
import org.gogood.navigation.TripsRoutes
import org.gogood.platform.ExternalRoutes

class ContextMenuViewModel(
    val appState: MutableStateFlow<AppState>,
    private val navigator: AppNavigator,
    private val externalRoutes: ExternalRoutes,
) : ViewModel() {
    fun onActionButtonClick(action: Action) {
        when (action) {
            is Action.Call -> externalRoutes.launchDialer(action.phoneNumber)
            is Action.Directions -> externalRoutes.launchMap(action.location)
            is Action.Share -> externalRoutes.launchShare(action.content)
            is Action.Website -> externalRoutes.launchBrowser(action.url)
            else -> {}
        }
    }

    fun onAddToTrip(
        data: Tileable,
        trip: Trip,
    ) {
        if (data is ContentItem) {
            // TODO(scottKeller): Where does this go?
            Logger.d("onAddToTrip: ${data.title} to ${trip.name}")
        }
    }

    fun onAddToFavorites(
        data: Tileable,
        favoriteList: FavoriteList,
    ) {
        if (data is ContentItem) {
            // TODO(scottKeller): Where does this go?
            Logger.d("onAddToTrip: ${data.title} to ${favoriteList.name}")
        }
    }

    fun onNewTrip(data: Tileable) {
        if (data is ContentItem) {
            navigator.navigate(TripsRoutes.Add)
        }
    }

    fun onNewFavorites(data: Tileable) {
        if (data is ContentItem) {
            navigator.navigate(FavoritesRoutes.Add)
        }
    }
}
