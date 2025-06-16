package org.gogood.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import org.gogood.navigation.navigator.NavigatorRoot
import org.gogood.navigation.navigator.Route
import org.gogood.views.contentItem.ContentItemView
import org.gogood.views.trips.TripView
import org.gogood.views.trips.TripsListView
import org.gogood.views.trips.TripsViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun TripsNav(navController: NavHostController) {
    val viewModel = koinViewModel<TripsViewModel>()

    NavigatorRoot(
        navController = navController,
        startDestination = TripsRoutes.List.route,
    ) {
        composable(TripsRoutes.List.route) {
            TripsListView(viewModel)
        }

        composable(TripsRoutes.Trip.route) {
            TripView(viewModel)
        }

        composable(TripsRoutes.Detail.route) {
            ContentItemView(AppSection.Trips)
        }
    }
}

sealed class TripsRoutes(route: String) : Route(route) {
    data object List : TripsRoutes("trips/list")

    data object Trip : TripsRoutes("trips/trip")

    data object Detail : TripsRoutes("trips/detail")

    data object Add : TripsRoutes("trips/add")

    data object Edit : TripsRoutes("trips/edit")
}
