package org.gogood.views.trips

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import gogood.composeapp.generated.resources.Res
import gogood.composeapp.generated.resources.trips_new_button
import gogood.composeapp.generated.resources.trips_title
import org.gogood.data.model.Trip
import org.gogood.ui.components.GGAppBar
import org.gogood.ui.components.GGTextButton
import org.gogood.ui.components.GGTileListLarge
import org.jetbrains.compose.resources.stringResource

@Composable
fun TripsListView(viewModel: TripsViewModel) {
    val state by viewModel.appState.collectAsStateWithLifecycle()
    TripsListView(
        trips = state.trips,
        onTripClick = { viewModel.navigateToTrip(it) },
        onNewClick = { viewModel.navigateToNewTrip() },
    )
}

@Composable
fun TripsListView(
    trips: List<Trip>,
    onTripClick: (trip: Trip) -> Unit,
    onNewClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets(0.dp),
        topBar = {
            GGAppBar(
                title = stringResource(Res.string.trips_title),
                actions = {
                    GGTextButton(
                        stringResource(Res.string.trips_new_button),
                    ) { onNewClick() }
                },
            )
        },
    ) { contentPadding ->
        GGTileListLarge(
            items = trips,
            key = { it.id },
            onTileClick = onTripClick,
            modifier =
                Modifier.padding(
                    PaddingValues(top = contentPadding.calculateTopPadding()),
                ),
        )
    }
}
