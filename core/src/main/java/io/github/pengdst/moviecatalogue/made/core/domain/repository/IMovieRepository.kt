package io.github.pengdst.moviecatalogue.made.core.domain.repository

import io.github.pengdst.moviecatalogue.made.core.domain.models.Movie
import io.github.pengdst.moviecatalogue.made.core.domain.models.TvShow
import io.github.pengdst.moviecatalogue.made.core.data.vo.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Created on 5/19/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
interface IMovieRepository {

    fun getUpcomingMovies(): Flow<Resource<List<Movie>>>
    fun getOnAirTvShows(): Flow<Resource<List<TvShow>>>

    fun getBookmarkedMovies(): Flow<List<Movie>>
    fun getBookmarkedTvShows(): Flow<List<TvShow>>

    fun getMovie(movieId: String): Flow<Resource<Movie>>
    fun getTv(tvShowId: String): Flow<Resource<TvShow>>

    suspend fun setBookmarkedTvShow(tvShow: TvShow)
    suspend fun setBookmarkedMovie(movie: Movie)

}