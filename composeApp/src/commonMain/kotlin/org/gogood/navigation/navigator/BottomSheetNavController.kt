package org.gogood.navigation.navigator

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.MutableStateFlow
import org.gogood.ui.components.GGBottomSheet
import org.koin.compose.koinInject

class BottomSheetNavController {
    private val navigator = BottomSheetNavigator()
    private val backstack = ArrayDeque<Route>()

    fun navigate(route: Route) {
        if (navigator.destinations.containsKey(route)) {
            if (route.route != navigator.currentBottomSheet.value?.route) {
                backstack.addLast(route)
                navigator.currentBottomSheet.value = backstack.lastOrNull()
            }
        }
    }

    fun navigateUp(): Boolean {
        return if (backstack.isNotEmpty()) {
            backstack.removeLast()
            navigator.currentBottomSheet.value = backstack.lastOrNull()
            true
        } else false
    }

    fun createGraph(builder: BottomSheetNavigator.() -> Unit): BottomSheetNavigator {
        builder.invoke(navigator)
        return navigator
    }
}

@Composable
fun rememberBottomSheetNavController() = remember {
    BottomSheetNavController()
}

class BottomSheetNavigator {
    val destinations: MutableMap<Route, @Composable () -> Unit> = mutableMapOf()

    val currentBottomSheet = MutableStateFlow<Route?>(null)

    fun getContent() = destinations[currentBottomSheet.value]
}

fun BottomSheetNavigator.bottomSheet(
    route: Route,
    content: @Composable () -> Unit,
) {
    destinations[route] = content
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetNavigatorRoot(
    bottomSheetNavController: BottomSheetNavController,
    builder: BottomSheetNavigator.() -> Unit,
) {
    val navigator = koinInject<AppNavigator>()
    val bottomSheetNavigator = remember { bottomSheetNavController.createGraph(builder) }

    DisposableEffect(bottomSheetNavController) {
        navigator.subscribe(bottomSheetNavController)
        onDispose { navigator.unsubscribe(bottomSheetNavController) }
    }

    val sheet by bottomSheetNavigator.currentBottomSheet.collectAsState()

    GGBottomSheet(
        showBottomSheet = sheet != null,
        showHandle = false,
        onDismiss = { navigator.navigateUp() },
    ) {
        Box(Modifier.fillMaxSize()) {
            bottomSheetNavigator.getContent()?.invoke()
        }
    }
}
