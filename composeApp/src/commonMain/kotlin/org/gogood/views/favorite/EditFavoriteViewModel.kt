package org.gogood.views.favorite

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.gogood.AppState
import org.gogood.navigation.navigator.AppNavigator

class EditFavoriteViewModel(
    val appState: MutableStateFlow<AppState>,
    private val navigator: AppNavigator,
) : ViewModel() {
    fun navigateUp() {
        navigator.navigateUp()
    }
}
