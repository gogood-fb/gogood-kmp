package org.gogood.views.contentItem

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import org.gogood.AppState
import org.gogood.data.model.ContentItem
import org.gogood.navigation.navigator.AppNavigator
import org.gogood.navigation.AppRoutes

class ContentItemViewModel(
    val appState: MutableStateFlow<AppState>,
    private val navigator: AppNavigator,
) : ViewModel() {
    fun navigateUp() {
        navigator.navigateUp()
    }

    fun navigateToGallery(contentItem: ContentItem) {
        appState.update { it.copy(selectedGalleryData = contentItem) }
        navigator.navigate(AppRoutes.Gallery)
    }
}
