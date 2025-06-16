package org.gogood.views

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.gogood.navigation.AppTabs
import org.gogood.ui.components.GGNavigationBar

@Composable
fun HomeView(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    var selected by remember { mutableStateOf(0) }
    val controllers =
        buildList {
            repeat(AppTabs.size) {
                add(rememberNavController())
            }
        }

    Scaffold(
        contentWindowInsets = WindowInsets.navigationBars,
        bottomBar = {
            GGNavigationBar(AppTabs, selected, { selected = it })
        },
    ) { contentPadding ->
        Crossfade(selected) {
            Box(
                Modifier.padding(
                    top = contentPadding.calculateTopPadding(),
                    bottom = contentPadding.calculateBottomPadding(),
                ),
            ) {
                AppTabs[selected].content.invoke(controllers[selected])
            }
        }
    }
}
