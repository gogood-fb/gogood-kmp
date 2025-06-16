package org.gogood.views.contentItem

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mikepenz.markdown.m3.Markdown
import org.gogood.data.model.ContentItem
import org.gogood.navigation.AppSection
import org.gogood.ui.StatusBarStyle
import org.gogood.ui.StatusBarStyler
import org.gogood.ui.components.CollapsingHeaderLayout
import org.gogood.ui.components.GGAppBarCollapsed
import org.gogood.ui.components.GGCarouselIndicator
import org.gogood.ui.components.GGImagePager
import org.gogood.views.contextMenu.ContextMenu
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ContentItemView(
    section: AppSection,
    viewModel: ContentItemViewModel = koinViewModel<ContentItemViewModel>(),
) {
    val state by viewModel.appState.collectAsStateWithLifecycle()
    var showContextMenu by remember { mutableStateOf(false) }

    StatusBarStyler(StatusBarStyle.DARK)

    when (section) {
        AppSection.Explore -> state.selectedContentItemExplore
        AppSection.Trips -> state.selectedContentItemTrips
        AppSection.Favorites -> state.selectedContentItemFavorites
    }?.let { contentItem ->
        ContentItemView(
            contentItem = contentItem,
            onBackClick = { viewModel.navigateUp() },
            onContextClick = { showContextMenu = true },
            onNavigateToGallery = { viewModel.navigateToGallery(contentItem) },
        )

        ContextMenu(
            tileable = contentItem,
            showBottomSheet = showContextMenu,
            onDismiss = { showContextMenu = false },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentItemView(
    contentItem: ContentItem,
    onBackClick: () -> Unit,
    onContextClick: () -> Unit,
    onNavigateToGallery: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val images = contentItem.additionalImageUrls.take(5)
    val pagerState = rememberPagerState(pageCount = { images.size })

    CollapsingHeaderLayout(
        modifier = modifier,
        headerBackground = {
            GGImagePager(
                images = images,
                pagerState = pagerState,
                showIndicator = false,
                onImageClick = { onNavigateToGallery() },
                modifier = Modifier.matchParentSize(),
            )
        },
        expandedContent = {
            Box(Modifier.matchParentSize()) {
                ExpandedHeader(contentItem, it)
                GGCarouselIndicator(
                    images.size,
                    pagerState.currentPage,
                    Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 15.dp)
                        .alpha(it),
                )
            }
        },
        collapsedContent = {
            GGAppBarCollapsed(
                contentItem.title,
                it,
                onBackClick,
                onContextClick,
            )
        },
        content = { Content(contentItem) },
    )
}

@Composable
private fun BoxScope.ExpandedHeader(
    contentItem: ContentItem,
    alpha: Float,
    modifier: Modifier = Modifier,
) {
    val address = remember { contentItem.getAddress() }
    Column(
        modifier
            .alpha(alpha)
            .align(Alignment.BottomStart)
            .fillMaxWidth()
            .padding(horizontal = 22.dp, vertical = 24.dp),
    ) {
        Text(
            text = contentItem.title,
            fontSize = 32.sp,
            lineHeight = 38.sp,
            color = Color.White,
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = contentItem.subtitle,
            fontSize = 16.sp,
            lineHeight = 31.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
        )

        Spacer(modifier = Modifier.height(12.dp))

        HorizontalDivider(color = Color.White, modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = address,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun Content(contentItem: ContentItem) {
    Box(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        Markdown(
            contentItem.contentMarkdown,
            modifier =
                Modifier.fillMaxWidth()
                    .padding(horizontal = 42.dp, vertical = 25.dp),
        )
    }
}
