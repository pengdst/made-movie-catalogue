package io.github.pengdst.moviecatalogue.made.di

import io.github.pengdst.moviecatalogue.made.ui.detail.DetailViewModel
import io.github.pengdst.moviecatalogue.made.ui.home.HomeViewModel
import io.github.pengdst.moviecatalogue.made.ui.home.sections.movie.MovieListAdapter
import io.github.pengdst.moviecatalogue.made.ui.home.sections.tv.TvShowListAdapter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}

val appModule = module {
    factory { MovieListAdapter() }
    factory { TvShowListAdapter() }
}