package org.gogood.views.gallery

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.gogood.AppState
import org.gogood.navigation.navigator.AppNavigator

class GalleryViewModel(
    val appState: MutableStateFlow<AppState>,
    private val navigator: AppNavigator,
) : ViewModel() {
    fun navigateUp() {
        navigator.navigateUp()
    }
}
