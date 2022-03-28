package io.github.pengdst.moviecatalogue.made.core.data.source.remote

import android.util.Log
import io.github.pengdst.moviecatalogue.made.core.data.source.remote.helpers.ApiResponse
import io.github.pengdst.moviecatalogue.made.core.data.source.remote.retrofit.models.MovieDto
import io.github.pengdst.moviecatalogue.made.core.data.source.remote.retrofit.models.TvShowDto
import io.github.pengdst.moviecatalogue.made.core.data.source.remote.retrofit.response.MovieResponse
import io.github.pengdst.moviecatalogue.made.core.data.source.remote.retrofit.response.TvResponse
import io.github.pengdst.moviecatalogue.made.core.data.source.remote.retrofit.routes.MovieRoute
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * Created on 5/20/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
class MovieRemoteSource(
    private val movieRoute: MovieRoute
) {

    suspend fun getUpcomingMovies(): Flow<ApiResponse<MovieResponse>> {
        return flow {
            try {
                val response = movieRoute.getUpcomingMovies()
                val movieResponse = response.body() ?: return@flow emit(ApiResponse.Empty)
                emit(if (movieResponse.results.isNullOrEmpty()) ApiResponse.Empty else ApiResponse.Success(movieResponse))
            }catch (t: Throwable) {
                emit(ApiResponse.Error(t.message.toString()))
                Log.e("RemoteDataSource", t.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMovie(movieId: String): Flow<ApiResponse<MovieDto>> {
        return flow {
            try {
                val response = movieRoute.getMovie(movieId)
                val movieDto = response.body() ?: return@flow emit(ApiResponse.Error(response.message().toString()))
                emit(ApiResponse.Success(movieDto))
            }catch (t: Throwable) {
                emit(ApiResponse.Error(t.message.toString()))
                Log.e("RemoteDataSource", t.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getOnAirTvShows(): Flow<ApiResponse<TvResponse>> {
        return flow {
            try {
                val response = movieRoute.getTvOnAir()
                val tvResponse = response.body() ?: return@flow emit(ApiResponse.Empty)
                emit(if (tvResponse.results.isNullOrEmpty()) ApiResponse.Empty else ApiResponse.Success(tvResponse))
            }catch (t: Throwable) {
                emit(ApiResponse.Error(t.message.toString()))
                Log.e("RemoteDataSource", t.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTvShow(tvId: String): Flow<ApiResponse<TvShowDto>> {
        return flow {
            try {
                val response = movieRoute.getTv(tvId)
                val tvDto = response.body() ?: return@flow emit(ApiResponse.Error(response.message().toString()))
                emit(ApiResponse.Success(tvDto))
            }catch (t: Throwable) {
                emit(ApiResponse.Error(t.message.toString()))
                Log.e("RemoteDataSource", t.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}