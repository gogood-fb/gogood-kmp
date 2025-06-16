package org.gogood.platform

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import org.gogood.data.model.Location

interface MapViewProvider {
    fun createMapView(locations: List<Location>): @Composable BoxScope.() -> Unit
}
