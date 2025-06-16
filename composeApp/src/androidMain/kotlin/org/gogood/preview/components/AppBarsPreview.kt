package org.gogood.preview.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import org.gogood.navigation.AppSection
import org.gogood.ui.components.GGAppBar
import org.gogood.ui.components.GGAppBarCollapsed
import org.gogood.ui.components.GGAppBarSecondary
import org.gogood.ui.components.GGNavigationBar
import org.gogood.ui.components.GGTextButton
import org.gogood.ui.theme.AppTheme
import org.gogood.ui.theme.WidgetPreview

@PreviewLightDark
@Composable
private fun GGAppBarPreview(modifier: Modifier = Modifier) {
    AppTheme.WidgetPreview {
        GGAppBar(
            "Sample title",
            {},
            actions = {
                GGTextButton(text = "Sample text") {}
            },
        )
    }
}

@PreviewLightDark
@Composable
private fun GGAppBarSecondaryPreview() {
    AppTheme.WidgetPreview {
        GGAppBarSecondary("Sample title", {})
    }
}

@PreviewLightDark
@Composable
private fun GGAppBarCollapsedPreview() {
    AppTheme.WidgetPreview {
        GGAppBarCollapsed("Sample title", 1f, {}, {})
    }
}

@PreviewLightDark
@Composable
private fun GGNavigationBarPreview() {
    val items =
        listOf(
            AppSection.Explore,
            AppSection.Trips,
            AppSection.Favorites,
        )

    AppTheme.WidgetPreview {
        GGNavigationBar(items, 0, {})
    }
}
