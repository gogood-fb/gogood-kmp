package org.gogood.preview.views

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import org.gogood.ui.PreviewData
import org.gogood.ui.theme.AppTheme
import org.gogood.ui.theme.ScreenPreview
import org.gogood.views.trips.AddTripView
import org.gogood.views.trips.EditTripView
import org.gogood.views.trips.TripView
import org.gogood.views.trips.TripsListView

@PreviewLightDark
@Composable
private fun TripsListViewPreview() {
    AppTheme.ScreenPreview {
        TripsListView(
            trips = PreviewData.trips,
            onTripClick = {},
            {},
        )
    }
}

@PreviewLightDark
@Composable
private fun TripViewPreview() {
    AppTheme.ScreenPreview {
        TripView(
            trip = PreviewData.trips.first(),
            mapView = {},
            {},
            {},
            {},
        )
    }
}

@PreviewLightDark
@Composable
private fun AddTripViewPreview() {
    AppTheme.ScreenPreview {
        AddTripView({})
    }
}

@PreviewLightDark
@Composable
private fun EditTripViewPreview() {
    AppTheme.ScreenPreview {
        EditTripView(
            trip = PreviewData.trips.first(),
            {},
        )
    }
}
