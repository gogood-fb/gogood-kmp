package org.gogood.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import gogood.composeapp.generated.resources.Res
import gogood.composeapp.generated.resources.ico_explore
import gogood.composeapp.generated.resources.ico_favorite
import gogood.composeapp.generated.resources.ico_trips
import gogood.composeapp.generated.resources.nav_explore
import gogood.composeapp.generated.resources.nav_favorite
import gogood.composeapp.generated.resources.nav_trips
import org.gogood.navigation.navigator.BottomSheetNavigatorRoot
import org.gogood.navigation.navigator.NavigatorRoot
import org.gogood.navigation.navigator.Route
import org.gogood.navigation.navigator.bottomSheet
import org.gogood.navigation.navigator.rememberBottomSheetNavController
import org.gogood.views.HomeView
import org.gogood.views.favorite.AddCollectionView
import org.gogood.views.favorite.EditCollectionView
import org.gogood.views.gallery.GalleryView
import org.gogood.views.trips.AddTripView
import org.gogood.views.trips.EditTripView
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

@Composable
fun AppNav(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val bottomSheetNavController = rememberBottomSheetNavController()

    Box(Modifier.fillMaxSize()) {
        NavigatorRoot(
            navController = navController,
            startDestination = AppRoutes.Home.route,
            modifier = modifier,
        ) {
            composable(AppRoutes.Home.route) {
                HomeView(navController)
            }

            composable(AppRoutes.Gallery.route) {
                GalleryView()
            }
        }

        BottomSheetNavigatorRoot(
            bottomSheetNavController,
        ) {
            bottomSheet(TripsRoutes.Add) {
                AddTripView()
            }

            bottomSheet(TripsRoutes.Edit) {
                EditTripView()
            }

            bottomSheet(FavoritesRoutes.Add) {
                AddCollectionView()
            }

            bottomSheet(FavoritesRoutes.Edit) {
                EditCollectionView()
            }
        }
    }
}

sealed class AppRoutes(route: String) : Route(route) {
    data object Home : ExploreRoutes("home")

    data object Gallery : ExploreRoutes("gallery")
}

val AppTabs =
    listOf(
        AppSection.Explore,
        AppSection.Trips,
        AppSection.Favorites,
    )

sealed class AppSection(
    val labelRes: StringResource,
    val iconRes: DrawableResource,
    val content: @Composable (navController: NavHostController) -> Unit,
) {
    data object Explore : AppSection(
        labelRes = Res.string.nav_explore,
        iconRes = Res.drawable.ico_explore,
        content = { ExploreNav(it) },
    )

    data object Trips : AppSection(
        labelRes = Res.string.nav_trips,
        iconRes = Res.drawable.ico_trips,
        content = { TripsNav(it) },
    )

    data object Favorites : AppSection(
        labelRes = Res.string.nav_favorite,
        iconRes = Res.drawable.ico_favorite,
        content = { FavoritesNav(it) },
    )
}
