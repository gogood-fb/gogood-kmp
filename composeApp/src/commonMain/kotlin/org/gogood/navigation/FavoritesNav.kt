package org.gogood.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import org.gogood.navigation.navigator.NavigatorRoot
import org.gogood.navigation.navigator.Route
import org.gogood.views.contentItem.ContentItemView
import org.gogood.views.favorite.CollectionView
import org.gogood.views.favorite.CollectionsListView
import org.gogood.views.favorite.FavoriteViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun FavoritesNav(navController: NavHostController) {
    val viewModel = koinViewModel<FavoriteViewModel>()

    NavigatorRoot(
        navController = navController,
        startDestination = FavoritesRoutes.List.route,
    ) {
        composable(FavoritesRoutes.List.route) {
            CollectionsListView(viewModel)
        }

        composable(FavoritesRoutes.Collection.route) {
            CollectionView(viewModel)
        }

        composable(FavoritesRoutes.Detail.route) {
            ContentItemView(AppSection.Favorites)
        }
    }
}

sealed class FavoritesRoutes(route: String) : Route(route) {
    data object List : FavoritesRoutes("favorite/list")

    data object Collection : FavoritesRoutes("favorite/collection")

    data object Detail : FavoritesRoutes("favorite/detail")

    data object Add : FavoritesRoutes("favorite/add")

    data object Edit : FavoritesRoutes("favorite/edit")
}
