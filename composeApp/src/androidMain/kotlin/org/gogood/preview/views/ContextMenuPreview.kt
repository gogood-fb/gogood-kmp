package org.gogood.preview.views

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import org.gogood.ui.PreviewData
import org.gogood.ui.theme.AppTheme
import org.gogood.ui.theme.WidgetPreview
import org.gogood.views.contextMenu.ContextMenuContent
import org.gogood.views.contextMenu.ContextMenuView

@PreviewLightDark
@Composable
private fun ContextMenuPreview() {
    AppTheme.WidgetPreview {
        ContextMenuContent(
            tileable = PreviewData.contentItems.first(),
            trips = PreviewData.trips,
            favorites = PreviewData.favorites,
            contentView = ContextMenuView.DEFAULT,
            onActionClick = {},
            onAddToTrip = {},
            onNewTrip = {},
            onAddToFavorites = {},
            onNewFavorites = {},
        )
    }
}

@PreviewLightDark
@Composable
private fun ContextMenuPreviewTrips() {
    AppTheme.WidgetPreview {
        ContextMenuContent(
            tileable = PreviewData.contentItems.first(),
            trips = PreviewData.trips,
            favorites = PreviewData.favorites,
            contentView = ContextMenuView.TRIPS,
            onActionClick = {},
            onAddToTrip = {},
            onNewTrip = {},
            onAddToFavorites = {},
            onNewFavorites = {},
        )
    }
}

@PreviewLightDark
@Composable
private fun ContextMenuPreviewFavorites() {
    AppTheme.WidgetPreview {
        ContextMenuContent(
            tileable = PreviewData.contentItems.first(),
            trips = PreviewData.trips,
            favorites = PreviewData.favorites,
            contentView = ContextMenuView.FAVORITES,
            onActionClick = {},
            onAddToTrip = {},
            onNewTrip = {},
            onAddToFavorites = {},
            onNewFavorites = {},
        )
    }
}
