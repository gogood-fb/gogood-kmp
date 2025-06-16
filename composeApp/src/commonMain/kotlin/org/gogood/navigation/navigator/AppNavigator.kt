package org.gogood.navigation.navigator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import co.touchlab.kermit.Logger
import org.koin.compose.koinInject

class AppNavigator {
    private var subscribers = ArrayDeque<NavController>()
    private var bottomSheetSubscribers = ArrayDeque<BottomSheetNavController>()

    fun subscribe(navController: NavController) {
        subscribers.addFirst(navController)
    }

    fun subscribe(bottomSheetNavController: BottomSheetNavController) {
        bottomSheetSubscribers.addFirst(bottomSheetNavController)
    }

    fun unsubscribe(navController: NavController) {
        subscribers.remove(navController)
    }

    fun unsubscribe(bottomSheetNavController: BottomSheetNavController) {
        bottomSheetSubscribers.remove(bottomSheetNavController)
    }

    fun navigateUp() {
        run nav@{
            bottomSheetSubscribers.forEach {
                if (it.navigateUp()) {
                    return@nav
                }
            }

            subscribers.forEach {
                if (it.navigateUp()) {
                    return@nav
                }
            }
        }
    }

    fun navigate(state: Route) {
        run nav@{
            subscribers.forEach {
                try {
                    it.navigate(state.route)
                    return@nav
                } catch (e: IllegalArgumentException) {
                    Logger.i("No handler for ${state.route}")
                }
            }

            bottomSheetSubscribers.forEach {
                try {
                    it.navigate(state)
                    return@nav
                } catch (e: IllegalArgumentException) {
                    Logger.i("No handler for ${state.route}")
                }
            }
        }
    }
}

abstract class Route(open val route: String)

@Composable
fun NavigatorRoot(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier,
    builder: NavGraphBuilder.() -> Unit,
) {
    val navigator = koinInject<AppNavigator>()

    DisposableEffect(navController) {
        navigator.subscribe(navController)
        onDispose { navigator.unsubscribe(navController) }
    }

    NavHost(
        navController = navController,
        startDestination = startDestination,
        builder = builder,
        modifier = modifier,
    )
}
