package org.gogood.views.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import gogood.composeapp.generated.resources.Res
import gogood.composeapp.generated.resources.faves_new_button
import gogood.composeapp.generated.resources.faves_title
import org.gogood.data.model.ContentItem
import org.gogood.data.model.FavoriteList
import org.gogood.ui.components.GGAppBar
import org.gogood.ui.components.GGTextButton
import org.gogood.ui.components.GGTileListSmall
import org.jetbrains.compose.resources.stringResource

@Composable
fun CollectionsListView(
    viewModel: FavoriteViewModel,
    modifier: Modifier = Modifier,
) {
    val state by viewModel.appState.collectAsStateWithLifecycle()
    CollectionsListView(
        collections = state.favorites,
        onCollectionClick = { viewModel.navigateToCollection(it) },
        onItemClick = { viewModel.navigateToContentItem(it) },
        onNewClick = { viewModel.navigateToNewCollection() },
        modifier = modifier,
    )
}

@Composable
fun CollectionsListView(
    collections: List<FavoriteList>,
    onCollectionClick: (FavoriteList) -> Unit,
    onItemClick: (ContentItem) -> Unit,
    onNewClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets(0.dp),
        topBar = {
            GGAppBar(
                title = stringResource(Res.string.faves_title),
                actions = {
                    GGTextButton(stringResource(Res.string.faves_new_button)) { onNewClick() }
                },
            )
        },
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier.padding(PaddingValues(top = contentPadding.calculateTopPadding())),
            contentPadding = PaddingValues(vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(collections, { it.id }) { favoriteList ->
                val items = favoriteList.getItemsOrdered()
                GGTileListSmall(
                    title = favoriteList.name,
                    onRowClick = { onCollectionClick(favoriteList) },
                    items = items,
                    key = { it.id },
                    onTileClick = onItemClick,
                )
            }
        }
    }
}
