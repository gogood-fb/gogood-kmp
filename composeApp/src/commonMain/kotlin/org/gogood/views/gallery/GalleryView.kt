package org.gogood.views.gallery

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import gogood.composeapp.generated.resources.Res
import gogood.composeapp.generated.resources.gallery_title
import org.gogood.data.model.Tileable
import org.gogood.ui.components.GGAppBarSecondary
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GalleryView(
    viewModel: GalleryViewModel = koinViewModel<GalleryViewModel>(),
    modifier: Modifier = Modifier,
) {
    val state by viewModel.appState.collectAsStateWithLifecycle()

    state.selectedGalleryData?.let {
        GalleryView(tileable = it, onBackClick = { viewModel.navigateUp() })
    }
}

@Composable
fun GalleryView(
    tileable: Tileable,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val data = tileable.toTile()

    Scaffold(
        topBar = {
            GGAppBarSecondary(
                title = stringResource(Res.string.gallery_title, data.title),
                onBackClick = onBackClick,
            )
        },
        modifier = modifier,
    ) { contentPadding ->
        LazyColumn(
            contentPadding =
                PaddingValues(
                    top = contentPadding.calculateTopPadding() + 10.dp,
                    bottom = contentPadding.calculateBottomPadding() + 10.dp,
                    start = 6.dp,
                    end = 6.dp,
                ),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier =
                Modifier.fillMaxSize()
                    .background(Color.Black.copy(0.2f)),
        ) {
            items(data.additionalImages) { imageUrl ->
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(15.dp))
                            .background(Color.Black.copy(0.2f)),
                )
            }
        }
    }
}
