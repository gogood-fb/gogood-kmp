package org.gogood.views.explore

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import gogood.composeapp.generated.resources.Res
import gogood.composeapp.generated.resources.explore_title
import gogood.composeapp.generated.resources.img_earth
import org.gogood.data.model.Destination
import org.gogood.ui.StatusBarStyle
import org.gogood.ui.StatusBarStyler
import org.gogood.ui.components.GGDestinationTile
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ExploreView(
    viewModel: ExploreViewModel,
    modifier: Modifier = Modifier,
) {
    val state by viewModel.appState.collectAsStateWithLifecycle()

    StatusBarStyler(StatusBarStyle.DARK)

    ExploreView(
        destinations = state.destinations,
        onDestinationClick = { viewModel.navigateToDestination(it) },
        modifier = modifier,
    )
}

@Composable
fun ExploreView(
    destinations: List<Destination>,
    onDestinationClick: (Destination) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier =
            modifier
                .fillMaxSize()
                .background(Color.Black)
                .windowInsetsPadding(WindowInsets.statusBars),
    ) {
        Image(
            painter = painterResource(Res.drawable.img_earth),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .offset(y = 80.dp),
        )

        Box(
            Modifier
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        0f to Color.Black,
                        0.25f to Color.Transparent,
                        0.82f to Color.Transparent,
                        1f to Color.Black,
                    ),
                ),
        ) { }

        Column(
            modifier =
                modifier
                    .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(45.dp))

            Text(
                text = stringResource(Res.string.explore_title),
                fontSize = 32.sp,
                lineHeight = 38.sp,
                fontWeight = FontWeight.ExtraLight,
                textAlign = TextAlign.Center,
                color = Color.White,
            )

            Spacer(Modifier.height(16.dp))

            val offsets =
                listOf(
                    Pair(71.dp, 77.dp),
                    Pair(175.dp, 294.dp),
                )

            Box(Modifier.fillMaxSize()) {
                destinations.forEachIndexed { index, dest ->
                    GGDestinationTile(
                        dest,
                        onClick = { onDestinationClick(dest) },
                        modifier = Modifier.offset(offsets[index].first, offsets[index].second),
                    )
                }
            }
        }
    }
}
