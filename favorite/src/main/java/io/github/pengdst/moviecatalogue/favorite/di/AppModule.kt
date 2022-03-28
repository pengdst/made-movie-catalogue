package io.github.pengdst.moviecatalogue.favorite.di

import io.github.pengdst.moviecatalogue.favorite.ui.FavoriteAdapter
import io.github.pengdst.moviecatalogue.favorite.ui.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteViewModel(get()) }
    factory { FavoriteAdapter() }
}