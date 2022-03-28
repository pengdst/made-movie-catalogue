package io.github.pengdst.moviecatalogue.made.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.pengdst.moviecatalogue.made.core.domain.usecase.MovieUsecase
import javax.inject.Inject

/**
 * Created on 5/16/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieUsecase: MovieUsecase
) : ViewModel() {

    fun getMovies() = movieUsecase.getUpcomingMovies().asLiveData()

    fun getTvShowList() = movieUsecase.getOnAirTvShows().asLiveData()

}