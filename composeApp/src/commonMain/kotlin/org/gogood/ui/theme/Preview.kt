package org.gogood.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

object AppTheme

@Composable
fun AppTheme.ScreenPreview(content: @Composable () -> Unit) {
    AppTheme {
        Box(
            Modifier
                .background(colorScheme.background)
                .fillMaxSize(),
        ) {
            content.invoke()
        }
    }
}

@Composable
fun AppTheme.WidgetPreview(widget: @Composable () -> Unit) {
    AppTheme {
        Column(
            Modifier
                .background(colorScheme.background),
        ) {
            widget.invoke()
        }
    }
}

@Composable
fun AppTheme.WidgetCatalogPreview(vararg widgets: @Composable () -> Unit) {
    WidgetCatalogPreview(widgets.toList())
}

@Composable
fun AppTheme.WidgetCatalogPreview(widgets: List<@Composable () -> Unit>) {
    AppTheme {
        Column(
            Modifier
                .background(colorScheme.background)
                .padding(16.dp),
        ) {
            widgets.forEachIndexed { index, widget ->
                widget.invoke()

                if (index != widgets.lastIndex) {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}
