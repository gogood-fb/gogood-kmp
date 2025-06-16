package org.gogood.di

import org.gogood.views.contentItem.ContentItemViewModel
import org.gogood.views.contextMenu.ContextMenuViewModel
import org.gogood.views.explore.ExploreViewModel
import org.gogood.views.favorite.EditFavoriteViewModel
import org.gogood.views.favorite.FavoriteViewModel
import org.gogood.views.gallery.GalleryViewModel
import org.gogood.views.trips.EditTripsViewModel
import org.gogood.views.trips.TripsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule =
    module {
        viewModel { ExploreViewModel(get(), get(), get(), get()) }
        viewModel { TripsViewModel(get(), get(), get()) }
        viewModel { FavoriteViewModel(get(), get()) }
        viewModel { ContentItemViewModel(get(), get()) }
        viewModel { ContextMenuViewModel(get(), get(), get()) }
        viewModel { GalleryViewModel(get(), get()) }
        viewModel { EditTripsViewModel(get(), get()) }
        viewModel { EditFavoriteViewModel(get(), get()) }
    }
