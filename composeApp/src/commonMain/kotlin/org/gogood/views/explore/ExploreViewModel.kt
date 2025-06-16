package org.gogood.views.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.gogood.AppState
import org.gogood.data.DataResource
import org.gogood.data.model.ContentItem
import org.gogood.data.model.Destination
import org.gogood.data.model.Location
import org.gogood.data.model.Weather
import org.gogood.data.repository.EntryRepository
import org.gogood.navigation.navigator.AppNavigator
import org.gogood.navigation.AppRoutes
import org.gogood.navigation.ExploreRoutes
import org.gogood.platform.WeatherProvider

class ExploreViewModel(
    val appState: MutableStateFlow<AppState>,
    private val navigator: AppNavigator,
    private val weatherProvider: WeatherProvider,
    private val entryRepository: EntryRepository,
) : ViewModel() {
    private val _weather = MutableStateFlow<Weather?>(null)
    val weather = _weather.asStateFlow()

    init {
        //This is for testing
        viewModelScope.launch(Dispatchers.Default) {
            entryRepository.getAnySingleEntry().collect {
                when(it){
                    is DataResource.Error -> {

                    }
                    DataResource.Loading -> {

                    }
                    is DataResource.Success -> {

                    }
                }
            }
        }
    }

    fun selectCategory() {}

    fun search() {}

    fun filterSearch() {}

    fun getWeather(location: Location) {
        viewModelScope.launch(Dispatchers.Default) {
            _weather.value = weatherProvider.getWeather(location)
        }
    }

    fun navigateUp() {
        navigator.navigateUp()
    }

    fun navigateToDestination(destination: Destination) {
        appState.update { it.copy(selectedDestination = destination) }
        navigator.navigate(ExploreRoutes.Destination)
    }

    fun navigateToGallery(destination: Destination) {
        appState.update { it.copy(selectedGalleryData = destination) }
        navigator.navigate(AppRoutes.Gallery)
    }

    fun navigateToContentItem(contentItem: ContentItem) {
        appState.update { it.copy(selectedContentItemExplore = contentItem) }
        navigator.navigate(ExploreRoutes.Details)
    }
}
