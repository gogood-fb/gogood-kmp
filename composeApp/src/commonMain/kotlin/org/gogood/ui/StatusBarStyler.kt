package org.gogood.ui

import androidx.compose.runtime.Composable

@Composable
expect fun StatusBarStyler(style: StatusBarStyle)

enum class StatusBarStyle {
    LIGHT,
    DARK,
    DEFAULT,
}
