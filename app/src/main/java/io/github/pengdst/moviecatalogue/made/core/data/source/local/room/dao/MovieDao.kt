package io.github.pengdst.moviecatalogue.made.core.data.source.local.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import io.github.pengdst.moviecatalogue.made.core.data.source.local.room.model.MovieEntity
import io.github.pengdst.moviecatalogue.made.core.data.source.local.room.model.TvShowEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created on 5/28/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun getMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies where isFavourite = 1")
    fun getBookmarkedMovie(): Flow<List<MovieEntity>>

    @Transaction
    @Query("SELECT * FROM movies WHERE id = :id")
    fun getMovieById(id: String): Flow<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Update
    suspend fun updateMovie(movie: MovieEntity)

    @Query("SELECT * FROM tv_shows")
    fun getTvShows(): Flow<List<TvShowEntity>>

    @Query("SELECT * FROM tv_shows where isFavourite = 1")
    fun getBookmarkedTvShow(): Flow<List<TvShowEntity>>

    @Query("SELECT * FROM tv_shows WHERE id = :id")
    fun getTvShow(id: String): Flow<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShows(tvShow: List<TvShowEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShow(tvShow: TvShowEntity)

    @Update
    suspend fun updateTvShow(tvShow: TvShowEntity)

}