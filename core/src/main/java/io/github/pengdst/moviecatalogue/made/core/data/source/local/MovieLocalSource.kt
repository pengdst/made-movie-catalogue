package io.github.pengdst.moviecatalogue.made.core.data.source.local

import io.github.pengdst.moviecatalogue.made.core.data.source.local.room.dao.MovieDao
import io.github.pengdst.moviecatalogue.made.core.data.source.local.room.model.MovieEntity
import io.github.pengdst.moviecatalogue.made.core.data.source.local.room.model.TvShowEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created on 6/1/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */

class MovieLocalSource(
    private val movieDao: MovieDao
) {

    fun getAllMovies(): Flow<List<MovieEntity>> = movieDao.getMovies()
    fun getBookmarkedMovies(): Flow<List<MovieEntity>> = movieDao.getBookmarkedMovie()
    fun getMovie(movieId: String) = movieDao.getMovieById(movieId)
    suspend fun saveMovie(movieEntity: MovieEntity) {
        movieDao.insertMovie(movieEntity)
    }
    suspend fun saveMovies(list: List<MovieEntity>) {
        movieDao.insertMovies(list)
    }

    fun getBookmarkedTvShows() = movieDao.getBookmarkedTvShow()

    fun getTvShows(): Flow<List<TvShowEntity>> = movieDao.getTvShows()
    fun getTvShow(tvId: String) = movieDao.getTvShow(tvId)
    suspend fun saveTvShow(tvShow: TvShowEntity) {
        movieDao.insertTvShow(tvShow)
    }
    suspend fun saveTvShows(list: List<TvShowEntity>) {
        movieDao.insertTvShows(list)
    }

    suspend fun updateTvShow(tvShowEntity: TvShowEntity) {
        movieDao.updateTvShow(tvShowEntity)
    }

    suspend fun updateMovie(movieEntity: MovieEntity) {
        movieDao.updateMovie(movieEntity)
    }

}