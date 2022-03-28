package io.github.pengdst.moviecatalogue.made.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.pengdst.moviecatalogue.made.core.data.vo.Resource
import io.github.pengdst.moviecatalogue.made.core.domain.models.Movie
import io.github.pengdst.moviecatalogue.made.core.domain.models.TvShow
import io.github.pengdst.moviecatalogue.made.core.domain.usecase.MovieUsecase
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created on 5/16/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val movieUsecase: MovieUsecase
) : ViewModel() {

    private var contentId: String = ""

    fun getMovie(): LiveData<Resource<Movie>> {
        return movieUsecase.getDetailMovie(contentId).asLiveData()
    }

    fun getTvShow(): LiveData<Resource<TvShow>> {
        return movieUsecase.getDetailTvShow(contentId).asLiveData()
    }

    fun setSelectedContent(contentId: String?){
        this.contentId = contentId ?: ""
    }

    fun setBookmark(movie: Movie) {
        viewModelScope.launch {
            movieUsecase.setFavouriteMovie(movie)
        }
    }

    fun setBookmark(tvShow: TvShow) {
        viewModelScope.launch {
            movieUsecase.setFavouriteTvShow(tvShow)
        }
    }
}