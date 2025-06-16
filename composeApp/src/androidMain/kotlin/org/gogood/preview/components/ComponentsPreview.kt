package org.gogood.preview.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import org.gogood.ui.components.GGCarouselIndicator
import org.gogood.ui.components.GGCategoryBar
import org.gogood.ui.components.GGMapBadgeLarge
import org.gogood.ui.components.GGMapBadgeSmall
import org.gogood.ui.components.GGSearchBar
import org.gogood.ui.components.GGSustainabilityScoreLarge
import org.gogood.ui.components.GGSustainabilityScoreSmall
import org.gogood.ui.theme.AppTheme
import org.gogood.ui.theme.WidgetCatalogPreview
import org.gogood.ui.theme.WidgetPreview

@PreviewLightDark
@Composable
private fun GGCategoryBarPreview() {
    AppTheme.WidgetPreview {
        GGCategoryBar(null, {}, Modifier.fillMaxWidth())
    }
}

@PreviewLightDark
@Composable
private fun GGSustainabilityScoreLargePreview() {
    AppTheme.WidgetCatalogPreview(
        { GGSustainabilityScoreLarge(60) },
        { GGSustainabilityScoreLarge(95) },
        { GGSustainabilityScoreLarge(100) },
    )
}

@PreviewLightDark
@Composable
private fun GGSustainabilityScoreSmallPreview() {
    AppTheme.WidgetCatalogPreview(
        { GGSustainabilityScoreSmall(60) },
        { GGSustainabilityScoreSmall(95) },
        { GGSustainabilityScoreSmall(100) },
    )
}

@PreviewLightDark
@Composable
private fun GGSearchBarPreview() {
    AppTheme.WidgetPreview {
        GGSearchBar(
            value = "",
            onValueChange = {},
            onFilterClick = {},
            locationName = "Belize",
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@PreviewLightDark
@Composable
private fun GGMapBadgeLargePreview() {
    AppTheme.WidgetPreview {
        GGMapBadgeLarge(2)
    }
}

@PreviewLightDark
@Composable
private fun GGMapBadgeSmallPreview() {
    AppTheme.WidgetPreview {
        GGMapBadgeSmall(2)
    }
}

@PreviewLightDark
@Composable
private fun GGCarouselIndicatorPreview() {
    AppTheme.WidgetPreview {
        GGCarouselIndicator(5, 1)
    }
}
