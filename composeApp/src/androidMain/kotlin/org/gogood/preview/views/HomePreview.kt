package org.gogood.preview.views

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.navigation.compose.rememberNavController
import org.gogood.ui.theme.AppTheme
import org.gogood.ui.theme.ScreenPreview
import org.gogood.views.HomeView

@PreviewLightDark
@Composable
private fun HomePreview() {
    AppTheme.ScreenPreview {
        HomeView(navController = rememberNavController())
    }
}
