package org.gogood.preview.views

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import org.gogood.ui.PreviewData
import org.gogood.ui.theme.AppTheme
import org.gogood.ui.theme.ScreenPreview
import org.gogood.views.contentItem.ContentItemView

@PreviewLightDark
@Composable
private fun ContentItemPreview() {
    AppTheme.ScreenPreview {
        ContentItemView(
            contentItem = PreviewData.contentItems.first(),
            {},
            {},
            {},
        )
    }
}
