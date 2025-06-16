package org.gogood.views.favorite

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import gogood.composeapp.generated.resources.Res
import gogood.composeapp.generated.resources.faves_edit_save_button
import gogood.composeapp.generated.resources.faves_edit_title
import org.gogood.data.model.FavoriteList
import org.gogood.ui.components.GGAppBar
import org.gogood.ui.components.GGTextButton
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun EditCollectionView(
    viewModel: EditFavoriteViewModel = koinViewModel(),
    modifier: Modifier = Modifier,
) {
    val state by viewModel.appState.collectAsStateWithLifecycle()
    state.selectedFavoriteList?.let {
        EditCollectionView(it, { viewModel.navigateUp() }, modifier)
    }
}

@Composable
fun EditCollectionView(
    collection: FavoriteList,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets(0.dp),
        topBar = {
            GGAppBar(
                title = stringResource(Res.string.faves_edit_title),
                onBackClick = onBackClick,
                actions = { GGTextButton(stringResource(Res.string.faves_edit_save_button), {}) },
            )
        },
    ) { contentPadding ->
        Text("EditCollectionView")
    }
}
