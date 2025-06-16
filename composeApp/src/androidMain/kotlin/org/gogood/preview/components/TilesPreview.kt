package org.gogood.preview.components

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import org.gogood.ui.PreviewData
import org.gogood.ui.components.GGDestinationTile
import org.gogood.ui.components.GGTileLarge
import org.gogood.ui.components.GGTileMedium
import org.gogood.ui.components.GGTileSmall
import org.gogood.ui.theme.AppTheme
import org.gogood.ui.theme.WidgetPreview

@PreviewLightDark
@Composable
private fun GGTileLargePreview() {
    AppTheme.WidgetPreview {
        GGTileLarge(PreviewData.contentItems[0], {})
    }
}

@PreviewLightDark
@Composable
private fun GGTileMediumPreview() {
    AppTheme.WidgetPreview {
        GGTileMedium(PreviewData.contentItems[0])
    }
}

@PreviewLightDark
@Composable
private fun GGTileSmallPreview() {
    AppTheme.WidgetPreview {
        GGTileSmall(PreviewData.contentItems[0], {})
    }
}

@PreviewLightDark
@Composable
private fun GGDestinationTilePreview() {
    val data = PreviewData.destinations[0]
    AppTheme.WidgetPreview {
        Surface(
            color = Color.Black,
        ) {
            GGDestinationTile(data, {})
        }
    }
}
