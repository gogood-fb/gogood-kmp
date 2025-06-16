package org.gogood.preview.views

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import org.gogood.data.model.Weather
import org.gogood.ui.PreviewData
import org.gogood.ui.theme.AppTheme
import org.gogood.ui.theme.ScreenPreview
import org.gogood.views.explore.DestinationView
import org.gogood.views.explore.ExploreView

@PreviewLightDark
@Composable
private fun ExploreViewPreview() {
    AppTheme.ScreenPreview {
        ExploreView(PreviewData.destinations, {})
    }
}

@PreviewLightDark
@Composable
private fun DestinationViewPreview() {
    AppTheme.ScreenPreview {
        DestinationView(
            destination = PreviewData.destinations[0],
            weather = Weather(20, 10, 5),
            contentItems = PreviewData.contentItems,
            onBackClick = {},
            onContextClick = {},
            onNavigateToGallery = {},
            onContentItemClick = {},
        )
    }
}
