package org.gogood.preview.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import org.gogood.data.model.Action
import org.gogood.data.model.ContentItem
import org.gogood.ui.components.GGActionButton
import org.gogood.ui.components.GGBackButton
import org.gogood.ui.components.GGCategoryButton
import org.gogood.ui.components.GGContextButton
import org.gogood.ui.components.GGMoreButton
import org.gogood.ui.components.GGTextButton
import org.gogood.ui.theme.AppTheme
import org.gogood.ui.theme.WidgetCatalogPreview

@PreviewLightDark
@Composable
private fun ButtonsPreview() {
    AppTheme.WidgetCatalogPreview(
        {
            Row {
                GGCategoryButton(
                    ContentItem.Type.DINING.iconRes,
                    ContentItem.Type.DINING.labelRes,
                    false,
                    {},
                )
                GGCategoryButton(
                    ContentItem.Type.OUTDOOR.iconRes,
                    ContentItem.Type.OUTDOOR.labelRes,
                    true,
                    {},
                )
            }
        },
        {
            Surface(color = MaterialTheme.colorScheme.secondary) {
                GGBackButton({})
            }
        },
        {
            Surface(color = MaterialTheme.colorScheme.secondary) {
                GGContextButton(
                    {},
                )
            }
        },
        {
            GGActionButton(
                action = Action.Call("asdasd"),
                {},
            )
        },
        { GGMoreButton({}) },
        { GGTextButton(text = "Sample Label") {} },
    )
}
