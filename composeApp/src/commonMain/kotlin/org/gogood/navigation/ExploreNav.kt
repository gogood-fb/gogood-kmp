package org.gogood.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import org.gogood.navigation.navigator.NavigatorRoot
import org.gogood.navigation.navigator.Route
import org.gogood.views.contentItem.ContentItemView
import org.gogood.views.explore.DestinationView
import org.gogood.views.explore.ExploreView
import org.gogood.views.explore.ExploreViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ExploreNav(navController: NavHostController) {
    val viewModel = koinViewModel<ExploreViewModel>()

    NavigatorRoot(
        navController = navController,
        startDestination = ExploreRoutes.Map.route,
    ) {
        composable(ExploreRoutes.Map.route) {
            ExploreView(viewModel)
        }

        composable(ExploreRoutes.Destination.route) {
            DestinationView(viewModel)
        }

        composable(ExploreRoutes.Details.route) {
            ContentItemView(AppSection.Explore)
        }
    }
}

sealed class ExploreRoutes(route: String) : Route(route) {
    data object Map : ExploreRoutes("explore/map")

    data object Destination : ExploreRoutes("explore/destination")

    data object Details : ExploreRoutes("explore/details")
}
