package org.gogood.ui

import android.graphics.Color
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext

@Composable
actual fun StatusBarStyler(style: StatusBarStyle) {
    val context = LocalContext.current as? ComponentActivity

    DisposableEffect(true) {
        Styler.onAppear(context, style)
        onDispose { Styler.onDispose(context) }
    }
}

private object Styler {
    private val defaultStyle = SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT)

    private var counter = 0

    fun onAppear(
        activity: ComponentActivity?,
        style: StatusBarStyle,
    ) {
        counter++

        val newStyle =
            when (style) {
                StatusBarStyle.LIGHT -> SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
                StatusBarStyle.DARK -> SystemBarStyle.dark(Color.TRANSPARENT)
                StatusBarStyle.DEFAULT -> defaultStyle
            }

        activity?.enableEdgeToEdge(
            statusBarStyle = newStyle,
            navigationBarStyle = newStyle,
        )
    }

    fun onDispose(activity: ComponentActivity?) {
        counter--
        if (counter == 0) {
            activity?.enableEdgeToEdge(
                statusBarStyle = defaultStyle,
                navigationBarStyle = defaultStyle,
            )
        }
    }
}
