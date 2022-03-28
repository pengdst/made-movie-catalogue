package io.github.pengdst.moviecatalogue.made.core.domain.usecase

import io.github.pengdst.moviecatalogue.made.core.domain.models.Movie
import io.github.pengdst.moviecatalogue.made.core.domain.models.TvShow
import io.github.pengdst.moviecatalogue.made.core.data.vo.Resource
import kotlinx.coroutines.flow.Flow

interface MovieUsecase {

    fun getUpcomingMovies(): Flow<Resource<List<Movie>>>
    fun getOnAirTvShows(): Flow<Resource<List<TvShow>>>

    fun getBookmarkedMovies(): Flow<List<Movie>>
    fun getBookmarkedTvShows(): Flow<List<TvShow>>

    fun getDetailMovie(movieId: String): Flow<Resource<Movie>>
    fun getDetailTvShow(tvShowId: String): Flow<Resource<TvShow>>

    suspend fun setFavouriteMovie(movie: Movie)
    suspend fun setFavouriteTvShow(tvShow: TvShow)

}