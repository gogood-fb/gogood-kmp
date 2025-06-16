package org.gogood.views.favorite

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import org.gogood.AppState
import org.gogood.data.model.ContentItem
import org.gogood.data.model.FavoriteList
import org.gogood.navigation.navigator.AppNavigator
import org.gogood.navigation.FavoritesRoutes

class FavoriteViewModel(
    val appState: MutableStateFlow<AppState>,
    private val navigator: AppNavigator,
) : ViewModel() {
    fun navigateUp() {
        navigator.navigateUp()
    }

    fun navigateToCollection(collection: FavoriteList) {
        appState.update { it.copy(selectedFavoriteList = collection) }
        navigator.navigate(FavoritesRoutes.Collection)
    }

    fun navigateToContentItem(contentItem: ContentItem) {
        appState.update { it.copy(selectedContentItemFavorites = contentItem) }
        navigator.navigate(FavoritesRoutes.Detail)
    }

    fun navigateToNewCollection() {
        navigator.navigate(FavoritesRoutes.Add)
    }
}
