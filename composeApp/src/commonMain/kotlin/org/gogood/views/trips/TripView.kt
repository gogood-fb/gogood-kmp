package org.gogood.views.trips

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.gogood.data.model.ContentItem
import org.gogood.data.model.Trip
import org.gogood.ui.StatusBarStyle
import org.gogood.ui.StatusBarStyler
import org.gogood.ui.components.CollapsingHeaderLayout
import org.gogood.ui.components.GGAppBarCollapsed
import org.gogood.ui.components.GGMapBadgeLarge
import org.gogood.ui.components.GGTileLarge
import org.gogood.views.contextMenu.ContextMenu

@Composable
fun TripView(viewModel: TripsViewModel) {
    val state by viewModel.appState.collectAsStateWithLifecycle()
    var showContextMenu by remember { mutableStateOf(false) }

    StatusBarStyler(StatusBarStyle.DARK)

    state.selectedTrip?.let { trip ->

        val mapView = remember(trip) { viewModel.getMapView(trip) }

        TripView(
            trip = trip,
            mapView = mapView,
            onBackClick = { viewModel.navigateUp() },
            onContextClick = { showContextMenu = true },
            onContentItemClick = { viewModel.navigateToContentItem(it) },
        )

        ContextMenu(
            tileable = trip,
            showBottomSheet = showContextMenu,
            onDismiss = { showContextMenu = false },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripView(
    trip: Trip,
    mapView: @Composable BoxScope.() -> Unit,
    onBackClick: () -> Unit,
    onContextClick: () -> Unit,
    onContentItemClick: (contentItem: ContentItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    CollapsingHeaderLayout(
        modifier = modifier,
        headerBackground = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                mapView.invoke(this)
            }
        },
        expandedContent = { ExpandedHeader(it, trip) },
        collapsedContent = { GGAppBarCollapsed(trip.name, it, onBackClick, onContextClick) },
        content = {
            Content(
                trip = trip,
                onClick = onContentItemClick,
            )
        },
    )
}

@Composable
private fun BoxScope.ExpandedHeader(
    alpha: Float,
    trip: Trip,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier
            .alpha(alpha)
            .align(Alignment.BottomStart)
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp),
    ) {
        Text(trip.name, fontSize = 28.sp, lineHeight = 33.sp, color = Color.White)
        Text(trip.description, fontSize = 20.sp, lineHeight = 31.sp, color = Color.White)
    }
}

@Composable
private fun Content(
    trip: Trip,
    onClick: (contentItem: ContentItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 30.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        val data = trip.getItemsOrdered()
        itemsIndexed(data, { _, item -> item.id }) { index, item ->
            Box {
                GGTileLarge(
                    tileable = item,
                    onClick = { onClick(item) },
                )
                GGMapBadgeLarge(
                    index + 1,
                    Modifier
                        .align(Alignment.TopStart)
                        .padding(start = 9.dp, top = 9.dp),
                )
            }
        }
    }
}
