package org.gogood.views.explore

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.AnimationState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateTo
import androidx.compose.animation.core.spring
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import gogood.composeapp.generated.resources.Res
import gogood.composeapp.generated.resources.explore_weather_format
import gogood.composeapp.generated.resources.explore_weather_unit
import kotlinx.coroutines.launch
import org.gogood.data.model.ContentItem
import org.gogood.data.model.Destination
import org.gogood.data.model.Weather
import org.gogood.ui.PreviewData.contentItems
import org.gogood.ui.StatusBarStyle
import org.gogood.ui.StatusBarStyler
import org.gogood.ui.components.CollapsingHeaderLayout
import org.gogood.ui.components.GGAppBarCollapsed
import org.gogood.ui.components.GGCarouselIndicator
import org.gogood.ui.components.GGCategoryBar
import org.gogood.ui.components.GGImagePager
import org.gogood.ui.components.GGSearchBar
import org.gogood.ui.components.GGTileListLarge
import org.gogood.ui.components.GGTileListSmall
import org.gogood.views.contextMenu.ContextMenu
import org.jetbrains.compose.resources.stringResource

@Composable
fun DestinationView(viewModel: ExploreViewModel) {
    val state by viewModel.appState.collectAsStateWithLifecycle()
    val weather by viewModel.weather.collectAsStateWithLifecycle()
    var showContextMenu by remember { mutableStateOf(false) }

    StatusBarStyler(StatusBarStyle.DARK)

    state.selectedDestination?.let { destination ->
        DestinationView(
            destination = destination,
            weather = weather,
            contentItems = state.contentItems,
            onBackClick = { viewModel.navigateUp() },
            onContextClick = { showContextMenu = true },
            onNavigateToGallery = { viewModel.navigateToGallery(destination) },
            onContentItemClick = { viewModel.navigateToContentItem(it) },
        )

        ContextMenu(
            tileable = destination,
            showBottomSheet = showContextMenu,
            onDismiss = { showContextMenu = false },
        )

        LaunchedEffect(destination) {
            viewModel.getWeather(destination.location)
        }
    }
}

@Suppress("MagicNumber")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DestinationView(
    destination: Destination,
    weather: Weather?,
    contentItems: List<ContentItem>,
    onBackClick: () -> Unit,
    onContextClick: () -> Unit,
    onNavigateToGallery: () -> Unit,
    onContentItemClick: (ContentItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    val images = destination.additionalImageUrls.take(5)
    val pagerState = rememberPagerState(pageCount = { images.size })
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    CollapsingHeaderLayout(
        scrollBehavior = scrollBehavior,
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
                ExpandedHeader(destination, weather, it)
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
                title = destination.name,
                collapsedFraction = it,
                onBackClick = onBackClick,
                onContextClick = { onContextClick() },
            )
        },
        content = {
            Content(
                destination = destination,
                contentItems = contentItems,
                scrollBehavior = scrollBehavior,
                onContentItemClick = onContentItemClick,
            )
        },
    )
}

@Composable
private fun BoxScope.ExpandedHeader(
    destination: Destination,
    weather: Weather?,
    alpha: Float,
    modifier: Modifier = Modifier,
) {
    CompositionLocalProvider(
        LocalContentColor provides Color.White,
    ) {
        Column(
            modifier
                .alpha(alpha)
                .align(Alignment.BottomStart)
                .padding(16.dp)
                .width(IntrinsicSize.Min),
        ) {
            Text(
                text = destination.name,
                fontSize = 64.sp,
                lineHeight = 64.sp,
                fontWeight = FontWeight.Thin,
            )

            Text(
                text = destination.getDateTimeString(),
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
            )

            Spacer(modifier = Modifier.height(10.dp))

            HorizontalDivider(color = Color.White)

            Spacer(modifier = Modifier.height(6.dp))

            Row {
                Text(
                    text = weather?.temperatureC?.toString() ?: "--",
                    fontSize = 44.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.White,
                )

                Text(
                    text = stringResource(Res.string.explore_weather_unit),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Light,
                )

                Spacer(Modifier.width(9.dp))

                Text(
                    text =
                        stringResource(
                            Res.string.explore_weather_format,
                            weather?.precipitation ?: 0,
                            weather?.humidity ?: 0,
                        ),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    lineHeight = 18.sp,
                    modifier = Modifier.align(Alignment.CenterVertically),
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

private enum class DestinationContent {
    SEARCH,
    ALL,
    CATEGORY,
}

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("MagicNumber")
@Composable
private fun Content(
    destination: Destination,
    contentItems: List<ContentItem>,
    scrollBehavior: TopAppBarScrollBehavior,
    onContentItemClick: (ContentItem) -> Unit,
) {
    var selectedCategory by remember { mutableStateOf<ContentItem.Type?>(null) }

    var containerHeight by remember { mutableStateOf(0) }

    var query by remember { mutableStateOf("") }

    Column(
        Modifier
            .fillMaxSize()
            .onGloballyPositioned { containerHeight = it.size.height },
    ) {
        val scope = rememberCoroutineScope()

        GGSearchBar(
            value = query,
            onValueChange = { query = it },
            onFilterClick = { /* TODO */ },
            locationName = destination.name,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .onFocusChanged {
                        if (it.isFocused) {
                            scope.launch {
                                AnimationState(initialValue = scrollBehavior.state.heightOffset)
                                    .animateTo(
                                        targetValue = scrollBehavior.state.heightOffsetLimit,
                                        animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
                                    ) { scrollBehavior.state.heightOffset = value }
                            }
                        }
                    }
                    .draggable(
                        orientation = Orientation.Vertical,
                        state =
                            rememberDraggableState { delta ->
                                scrollBehavior.state.heightOffset += delta
                            },
                    ),
        )

        GGCategoryBar(
            selectedType = selectedCategory,
            onCategoryClick = { selectedCategory = it },
            modifier =
                Modifier
                    .fillMaxWidth()
                    .draggable(
                        orientation = Orientation.Vertical,
                        state =
                            rememberDraggableState { delta ->
                                scrollBehavior.state.heightOffset += delta
                            },
                    ),
        )

        val heightDp = LocalDensity.current.run { containerHeight.toDp() }

        val contentViewType =
            when {
                query.isNotBlank() -> DestinationContent.SEARCH
                selectedCategory == null -> DestinationContent.ALL
                else -> DestinationContent.CATEGORY
            }

        Crossfade(contentViewType) {
            when (contentViewType) {
                DestinationContent.ALL -> {
                    AllCategoriesView(
                        onCategoryClick = { selectedCategory = it },
                        onContentItemClick = { onContentItemClick(it) },
                        modifier = Modifier.height(heightDp),
                    )
                }

                DestinationContent.CATEGORY ->
                    CategoryView(
                        contentItems = contentItems.filter { it.type == selectedCategory },
                        onContentItemClick = { onContentItemClick(it) },
                        modifier = Modifier.height(heightDp),
                    )

                DestinationContent.SEARCH ->
                    SearchView(
                        contentItems =
                            contentItems.filter {
                                val inCategory =
                                    if (selectedCategory != null) {
                                        it.type == selectedCategory
                                    } else {
                                        true
                                    }

                                inCategory && it.title.contains(query, ignoreCase = true)
                            },
                        onContentItemClick = { onContentItemClick(it) },
                        modifier = Modifier.height(heightDp),
                    )
            }
        }
    }
}

@Composable
private fun AllCategoriesView(
    onCategoryClick: (ContentItem.Type) -> Unit,
    onContentItemClick: (ContentItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        ContentItem.Type.entries.forEach { type ->
            item {
                GGTileListSmall(
                    title =
                        stringResource(type.labelRes)
                            .replace("\n", " "),
                    onRowClick = { onCategoryClick(type) },
                    items = contentItems.filter { it.type == type },
                    key = { it.id },
                    onTileClick = { onContentItemClick(it) },
                )
            }
        }
    }
}

@Composable
private fun CategoryView(
    contentItems: List<ContentItem>,
    onContentItemClick: (ContentItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    GGTileListLarge(
        items = contentItems,
        key = { it.id },
        onTileClick = { onContentItemClick(it) },
        modifier = modifier,
    )
}

@Composable
private fun SearchView(
    contentItems: List<ContentItem>,
    onContentItemClick: (ContentItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    GGTileListLarge(
        items = contentItems,
        key = { it.id },
        onTileClick = { onContentItemClick(it) },
        modifier = modifier,
    )
}
