package org.gogood.views.contextMenu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import gogood.composeapp.generated.resources.Res
import gogood.composeapp.generated.resources.ico_add_circle
import gogood.composeapp.generated.resources.ico_add_fav
import gogood.composeapp.generated.resources.ico_add_trip
import org.gogood.data.model.Action
import org.gogood.data.model.FavoriteList
import org.gogood.data.model.Tileable
import org.gogood.data.model.Trip
import org.gogood.ui.components.GGActionButton
import org.gogood.ui.components.GGBottomSheet
import org.gogood.ui.components.GGTextButton
import org.gogood.ui.components.GGTileMedium
import org.gogood.ui.theme.extendedColors
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

enum class ContextMenuView {
    DEFAULT, TRIPS, FAVORITES
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T : Tileable> ContextMenu(
    tileable: T,
    modifier: Modifier = Modifier,
    showBottomSheet: Boolean,
    onDismiss: () -> Unit,
) {
    val viewModel = koinViewModel<ContextMenuViewModel>()
    val state by viewModel.appState.collectAsStateWithLifecycle()
    var contentView by remember { mutableStateOf(ContextMenuView.DEFAULT) }

    GGBottomSheet(
        showBottomSheet = showBottomSheet,
        onDismiss = onDismiss,
        modifier = modifier,
    ) {
        ContextMenuContent(
            tileable = tileable,
            trips = state.trips,
            favorites = state.favorites,
            contentView = contentView,
            onActionClick = {
                when (it) {
                    Action.AddToFavorites -> contentView = ContextMenuView.FAVORITES
                    Action.AddToTrip -> contentView = ContextMenuView.TRIPS
                    else -> {
                        contentView = ContextMenuView.DEFAULT
                        viewModel.onActionButtonClick(it)
                    }
                }
            },
            onAddToTrip = { viewModel.onAddToTrip(tileable, it) },
            onNewTrip = { viewModel.onNewTrip(tileable) },
            onAddToFavorites = { viewModel.onAddToFavorites(tileable, it) },
            onNewFavorites = { viewModel.onNewFavorites(tileable) },
        )
    }
}

@Composable
fun ContextMenuContent(
    tileable: Tileable,
    trips: List<Trip>,
    favorites: List<FavoriteList>,
    contentView: ContextMenuView,
    onActionClick: (Action) -> Unit,
    onAddToTrip: (trip: Trip) -> Unit,
    onNewTrip: () -> Unit,
    onAddToFavorites: (favorites: FavoriteList) -> Unit,
    onNewFavorites: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val tileData = tileable.toTile()

    CompositionLocalProvider(
        LocalContentColor provides MaterialTheme.colorScheme.onBackground,
    ) {
        Column(
            modifier
                .fillMaxWidth()
                .windowInsetsPadding(WindowInsets.navigationBars),
        ) {
            GGTileMedium(
                tileable,
                Modifier.padding(start = 22.dp, end = 22.dp, top = 8.dp, bottom = 11.dp),
            )

            HorizontalDivider(color = MaterialTheme.extendedColors.textMuted.copy(0.2f))

            when (contentView) {
                ContextMenuView.TRIPS -> {
                    AddToTripsView(
                        trips = trips,
                        onAdd = onAddToTrip,
                        onNew = onNewTrip,
                    )
                }

                ContextMenuView.FAVORITES -> {
                    AddToFavoritesView(
                        favorites = favorites,
                        onAdd = onAddToFavorites,
                        onNew = onNewFavorites,
                    )
                }

                ContextMenuView.DEFAULT -> {}
            }

            LazyRow(
                Modifier
                    .heightIn(100.dp)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(vertical = 10.dp, horizontal = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                items(tileData.actions, { it.hashCode() }) { action ->
                    GGActionButton(action, { onActionClick(action) })
                }
            }
        }
    }
}

@Composable
private fun AddToTripsView(
    trips: List<Trip>,
    onAdd: (Trip) -> Unit,
    onNew: () -> Unit,
) {
    Column(Modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = "Save to My Trips",
                fontSize = 16.sp,
                modifier =
                Modifier
                    .weight(1f)
                    .padding(horizontal = 30.dp),
            )
            GGTextButton("New Trip") { onNew() }
        }
        LazyColumn(
            Modifier
                .fillMaxWidth()
                .height(150.dp),
        ) {
            items(trips, { it.id }) { trip ->
                AddRow(Res.drawable.ico_add_trip, trip.name) { onAdd(trip) }
            }
        }
    }
}

@Composable
private fun AddToFavoritesView(
    favorites: List<FavoriteList>,
    onAdd: (FavoriteList) -> Unit,
    onNew: () -> Unit,
) {
    Column(Modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = "Save to My Favorites",
                fontSize = 16.sp,
                modifier =
                Modifier
                    .weight(1f)
                    .padding(horizontal = 30.dp),
            )
            GGTextButton("New Collection") { onNew() }
        }
        LazyColumn(
            Modifier
                .fillMaxWidth()
                .height(150.dp),
        ) {
            items(favorites, { it.id }) { favorite ->
                AddRow(Res.drawable.ico_add_fav, favorite.name) { onAdd(favorite) }
            }
        }
    }
}

@Composable
fun AddRow(
    icon: DrawableResource,
    title: String,
    onAdd: () -> Unit,
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(start = 45.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painterResource(icon),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
        )

        Spacer(Modifier.width(10.dp))

        Text(
            text = title,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.weight(1f),
        )

        IconButton(onClick = { onAdd() }) {
            Icon(
                painterResource(Res.drawable.ico_add_circle),
                contentDescription = null,
                tint = MaterialTheme.extendedColors.textMuted,
            )
        }
    }
}
