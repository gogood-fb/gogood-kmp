package org.gogood.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.gogood.navigation.AppSection
import org.gogood.ui.theme.extendedColors
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GGAppBar(
    title: String,
    onBackClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    actions: @Composable RowScope.() -> Unit = {},
) {
    CenterAlignedTopAppBar(
        title = { Text(title, fontSize = 18.sp, fontWeight = FontWeight.SemiBold) },
        actions = {
            CompositionLocalProvider(
                LocalContentColor provides MaterialTheme.colorScheme.secondary,
            ) {
                actions.invoke(this)
            }
        },
        navigationIcon = {
            onBackClick?.let {
                CompositionLocalProvider(
                    LocalContentColor provides MaterialTheme.colorScheme.secondary,
                ) {
                    GGBackButton(onBackClick)
                }
            }
        },
        modifier = modifier,
        colors =
            TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
            ),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GGAppBarSecondary(
    title: String,
    onBackClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    actions: @Composable RowScope.() -> Unit = {},
) {
    CenterAlignedTopAppBar(
        title = { Text(title, fontSize = 18.sp, fontWeight = FontWeight.SemiBold) },
        actions = actions,
        navigationIcon = {
            onBackClick?.let {
                GGBackButton(onBackClick)
            }
        },
        modifier = modifier,
        colors =
            TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                titleContentColor = MaterialTheme.colorScheme.onSecondary,
                actionIconContentColor = MaterialTheme.colorScheme.onSecondary,
                navigationIconContentColor = MaterialTheme.colorScheme.onSecondary,
            ),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GGAppBarCollapsed(
    title: String,
    collapsedFraction: Float,
    onBackClick: () -> Unit,
    onContextClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = { Text(title, fontSize = 18.sp, fontWeight = FontWeight.SemiBold) },
        navigationIcon = { GGBackButton(onBackClick) },
        actions = { GGContextButton(onContextClick) },
        colors =
            TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent,
                titleContentColor = Color.White.copy(collapsedFraction),
                navigationIconContentColor = Color.White,
                actionIconContentColor = Color.White,
            ),
        modifier = modifier,
    )
}

@Composable
fun GGNavigationBar(
    navItems: List<AppSection>,
    selectedIndex: Int,
    onItemClick: (index: Int) -> Unit,
) {
    Column {
        HorizontalDivider(color = MaterialTheme.extendedColors.textMuted.copy(0.2f))
        NavigationBar(containerColor = MaterialTheme.colorScheme.background) {
            navItems.forEachIndexed { index, item ->
                NavigationBarItem(
                    selected = index == selectedIndex,
                    onClick = { onItemClick(index) },
                    icon = { Icon(painterResource(item.iconRes), null) },
                    label = { Text(stringResource(item.labelRes)) },
                    alwaysShowLabel = true,
                    colors =
                        NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            unselectedIconColor = MaterialTheme.extendedColors.textMuted,
                            selectedTextColor = MaterialTheme.colorScheme.onBackground,
                            unselectedTextColor = MaterialTheme.extendedColors.textMuted,
                            indicatorColor = Color.Transparent,
                        ),
                )
            }
        }
    }
}
