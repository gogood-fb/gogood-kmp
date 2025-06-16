package org.gogood.views.favorite

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.gogood.data.model.ContentItem
import org.gogood.data.model.FavoriteList
import org.gogood.ui.components.GGAppBar
import org.gogood.ui.components.GGTileListLarge

@Composable
fun CollectionView(viewModel: FavoriteViewModel) {
    val state by viewModel.appState.collectAsStateWithLifecycle()
    state.selectedFavoriteList?.let {
        CollectionView(
            favoriteList = it,
            onItemClick = { viewModel.navigateToContentItem(it) },
            onBackClick = { viewModel.navigateUp() },
        )
    }
}

@Composable
fun CollectionView(
    favoriteList: FavoriteList,
    onItemClick: (ContentItem) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets(0.dp),
        topBar = {
            GGAppBar(
                title = favoriteList.name,
                onBackClick = onBackClick,
            )
        },
    ) { contentPadding ->
        val items = favoriteList.getItemsOrdered()
        GGTileListLarge(
            items = items,
            key = { it.id },
            onTileClick = onItemClick,
            modifier =
                Modifier.padding(
                    PaddingValues(top = contentPadding.calculateTopPadding()),
                ),
        )
    }
}
