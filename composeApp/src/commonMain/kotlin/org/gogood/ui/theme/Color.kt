package org.gogood.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

private val primaryLight = Color(0xFF76A962)
private val onPrimaryLight = Color(0xFFFFFFFF)
private val secondaryLight = Color(0xFFD4765E)
private val onSecondaryLight = Color(0xFFFFFFFF)
private val tertiaryLight = Color(0xFF5C5F59)
private val onTertiaryLight = Color(0xFFFFFFFF)
private val backgroundLight = Color(0xFFFFFFFF)
private val onBackgroundLight = Color(0xFF000000)
private val surfaceLight = Color(0x0f76a962)
private val onSurfaceLight = Color(0xFF474747)
private val surfaceVariantLight = Color(0xFFE0E3E3)
private val onSurfaceVariantLight = Color(0xFF444748)

private val primaryDark = Color(0xFF76A962)
private val onPrimaryDark = Color(0xFFFFFFFF)
private val secondaryDark = Color(0xFFD4765E)
private val onSecondaryDark = Color(0xFFFFFFFF)
private val tertiaryDark = Color(0xFF5C5F59)
private val onTertiaryDark = Color(0xFFFFFFFF)
private val backgroundDark = Color(0xff191919)
private val onBackgroundDark = Color(0xFFE1E3DB)
private val surfaceDark = Color(0x0f76a962)
private val onSurfaceDark = Color(0xffc3c3c3)
private val surfaceVariantDark = Color(0xFF444748)
private val onSurfaceVariantDark = Color(0xFFC4C7C7)

private val textMutedLight = Color(0xFF8D8D8D)
private val textMutedDark = Color(0xFF8D8D8D)

@Immutable
data class ExtendedColorScheme(
    val textMuted: Color,
    val field: Color,
)

val lightScheme =
    lightColorScheme(
        primary = primaryLight,
        onPrimary = onPrimaryLight,
        secondary = secondaryLight,
        onSecondary = onSecondaryLight,
        tertiary = tertiaryLight,
        onTertiary = onTertiaryLight,
        background = backgroundLight,
        onBackground = onBackgroundLight,
        surface = surfaceLight,
        onSurface = onSurfaceLight,
        surfaceVariant = surfaceVariantLight,
        onSurfaceVariant = onSurfaceVariantLight,
    )

val darkScheme =
    darkColorScheme(
        primary = primaryDark,
        onPrimary = onPrimaryDark,
        secondary = secondaryDark,
        onSecondary = onSecondaryDark,
        tertiary = tertiaryDark,
        onTertiary = onTertiaryDark,
        background = backgroundDark,
        onBackground = onBackgroundDark,
        surface = surfaceDark,
        onSurface = onSurfaceDark,
        surfaceVariant = surfaceVariantDark,
        onSurfaceVariant = onSurfaceVariantDark,
    )

val LocalExtendedColors =
    staticCompositionLocalOf {
        ExtendedColorScheme(
            textMuted = Color.Unspecified,
            field = Color.Unspecified,
        )
    }

val extendedLight =
    ExtendedColorScheme(
        textMuted = textMutedLight,
        field = Color(0x80ededed),
    )

val extendedDark =
    ExtendedColorScheme(
        textMuted = textMutedDark,
        field = Color(0x80373737),
    )

val MaterialTheme.extendedColors
    @Composable @ReadOnlyComposable
    get() = LocalExtendedColors.current
