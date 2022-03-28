package io.github.pengdst.moviecatalogue.favorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.pengdst.moviecatalogue.made.core.domain.usecase.MovieUsecase
import io.github.pengdst.moviecatalogue.made.utils.DataStore
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created on 5/16/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val movieUsecase: MovieUsecase
) : ViewModel() {

    fun getMovies() = movieUsecase.getBookmarkedMovies()

    fun getTvShowList() = movieUsecase.getBookmarkedTvShows()

    fun getFavoriteData(type: String) = when (type) {
        DataStore.TYPE_MOVIE -> getMovies().map { list ->
            list.map {
                FavoriteData(
                    id = it.id,
                    title = it.title,
                    posterUrl = it.imagePosterUrl,
                    type = DataStore.TYPE_MOVIE
                )
            }
        }
        else -> getTvShowList().map { list ->
            list.map {
                FavoriteData(
                    id = it.id,
                    title = it.title,
                    posterUrl = it.imagePosterUrl,
                    type = DataStore.TYPE_TV_SHOW
                )
            }
        }
    }.asLiveData()

}