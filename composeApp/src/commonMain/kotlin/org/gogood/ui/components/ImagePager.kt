package org.gogood.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil3.compose.AsyncImage

@Composable
fun GGImagePager(
    images: List<String>,
    pagerState: PagerState = rememberPagerState(pageCount = { images.size }),
    showIndicator: Boolean = true,
    onImageClick: (index: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.matchParentSize(),
        ) { index ->
            AsyncImage(
                model = images[index],
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier =
                    modifier
                        .fillMaxSize()
                        .clickable { onImageClick(index) },
            )
        }

        if (showIndicator) {
            GGCarouselIndicator(
                images.size,
                index = pagerState.currentPage,
                modifier =
                    Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 15.dp)
                        .zIndex(1f),
            )
        }
    }
}
