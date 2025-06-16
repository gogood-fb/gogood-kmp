package org.gogood.preview.views

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import org.gogood.ui.PreviewData
import org.gogood.ui.theme.AppTheme
import org.gogood.ui.theme.ScreenPreview
import org.gogood.views.favorite.AddCollectionView
import org.gogood.views.favorite.EditCollectionView
import org.gogood.views.trips.TripView
import org.gogood.views.trips.TripsListView

@PreviewLightDark
@Composable
private fun CollectionsListViewPreview() {
    AppTheme.ScreenPreview {
        TripsListView(
            trips = PreviewData.trips,
            onTripClick = {},
            onNewClick = {},
        )
    }
}

@PreviewLightDark
@Composable
private fun CollectionViewPreview() {
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
private fun AddCollectionViewPreview() {
    AppTheme.ScreenPreview {
        AddCollectionView({})
    }
}

@PreviewLightDark
@Composable
private fun EditCollectionViewPreview() {
    AppTheme.ScreenPreview {
        EditCollectionView(
            collection = PreviewData.favorites.first(),
            {},
        )
    }
}
