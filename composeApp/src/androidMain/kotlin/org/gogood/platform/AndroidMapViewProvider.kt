package org.gogood.platform

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.gogood.data.model.Location

class AndroidMapViewProvider : MapViewProvider {
    override fun createMapView(locations: List<Location>): @Composable BoxScope.() -> Unit =
        {
            Box(Modifier.matchParentSize(), contentAlignment = Alignment.Center) {
                Text("This is an Android Map View")
            }
        }
}
