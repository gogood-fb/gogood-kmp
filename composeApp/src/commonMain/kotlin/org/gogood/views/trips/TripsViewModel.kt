package org.gogood.views.trips

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import org.gogood.AppState
import org.gogood.data.model.ContentItem
import org.gogood.data.model.Trip
import org.gogood.navigation.navigator.AppNavigator
import org.gogood.navigation.TripsRoutes
import org.gogood.platform.MapViewProvider

class TripsViewModel(
    val appState: MutableStateFlow<AppState>,
    private val navigator: AppNavigator,
    private val mapViewProvider: MapViewProvider,
) : ViewModel() {
    fun getMapView(trip: Trip): @Composable BoxScope.() -> Unit {
        val locations = trip.getItemsOrdered().map { it.location }
        return mapViewProvider.createMapView(locations)
    }

    fun navigateToContentItem(contentItem: ContentItem) {
        appState.update { it.copy(selectedContentItemTrips = contentItem) }
        navigator.navigate(TripsRoutes.Detail)
    }

    fun navigateToTrip(trip: Trip) {
        appState.update { it.copy(selectedTrip = trip) }
        navigator.navigate(TripsRoutes.Trip)
    }

    fun navigateUp() {
        navigator.navigateUp()
    }

    fun navigateToNewTrip() {
        navigator.navigate(TripsRoutes.Add)
    }
}
