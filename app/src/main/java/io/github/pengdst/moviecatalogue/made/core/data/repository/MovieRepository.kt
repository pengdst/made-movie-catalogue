package io.github.pengdst.moviecatalogue.made.core.data.repository

import io.github.pengdst.moviecatalogue.made.core.data.source.local.MovieLocalSource
import io.github.pengdst.moviecatalogue.made.core.data.source.local.mapper.MovieEntityMapper.toDomain
import io.github.pengdst.moviecatalogue.made.core.data.source.local.mapper.MovieEntityMapper.toEntity
import io.github.pengdst.moviecatalogue.made.core.data.source.local.mapper.TvShowEntityMapper.toDomain
import io.github.pengdst.moviecatalogue.made.core.data.source.local.mapper.TvShowEntityMapper.toEntity
import io.github.pengdst.moviecatalogue.made.core.data.source.remote.MovieRemoteSource
import io.github.pengdst.moviecatalogue.made.core.data.source.remote.helpers.ApiResponse
import io.github.pengdst.moviecatalogue.made.core.data.source.remote.mapper.MovieDtoMapper.toEntity
import io.github.pengdst.moviecatalogue.made.core.data.source.remote.mapper.TvDtoMapper.toEntity
import io.github.pengdst.moviecatalogue.made.core.data.source.remote.retrofit.models.MovieDto
import io.github.pengdst.moviecatalogue.made.core.data.source.remote.retrofit.models.TvShowDto
import io.github.pengdst.moviecatalogue.made.core.data.source.remote.retrofit.response.MovieResponse
import io.github.pengdst.moviecatalogue.made.core.data.source.remote.retrofit.response.TvResponse
import io.github.pengdst.moviecatalogue.made.core.domain.models.Movie
import io.github.pengdst.moviecatalogue.made.core.domain.models.TvShow
import io.github.pengdst.moviecatalogue.made.core.domain.repository.IMovieRepository
import io.github.pengdst.moviecatalogue.made.core.data.utils.NetworkBoundResource
import io.github.pengdst.moviecatalogue.made.core.data.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created on 5/19/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
class MovieRepository @Inject constructor(
    private val remote: MovieRemoteSource,
    private val local: MovieLocalSource
) : IMovieRepository {

    override fun getUpcomingMovies(): Flow<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, MovieResponse>() {
            override fun loadFromDB() =
                local.getAllMovies().map { it.toDomain() }

            override fun shouldFetch(data: List<Movie>?) = true

            override suspend fun createCall() =
                remote.getUpcomingMovies()

            override suspend fun saveCallResult(data: MovieResponse) {
                data.results?.toEntity()?.let {
                    local.saveMovies(it)
                }
            }
        }.asFlow()
    }

    override fun getBookmarkedMovies() = local.getBookmarkedMovies().map {
        it.toDomain()
    }

    override fun getMovie(movieId: String): Flow<Resource<Movie>> {
        return object : NetworkBoundResource<Movie, MovieDto>() {

            override fun loadFromDB(): Flow<Movie> {
                return local.getMovie(movieId).map { it.toDomain() }
            }

            override fun shouldFetch(data: Movie?) = data == null || !data.isFavourite

            override suspend fun createCall(): Flow<ApiResponse<MovieDto>> {
                return remote.getMovie(movieId)
            }

            override suspend fun saveCallResult(data: MovieDto) {
                local.saveMovie(data.toEntity())
            }
        }.asFlow()
    }

    override fun getOnAirTvShows(): Flow<Resource<List<TvShow>>> {
        return object : NetworkBoundResource<List<TvShow>, TvResponse>() {
            override fun loadFromDB() =
                local.getTvShows().map { it.toDomain() }

            override fun shouldFetch(data: List<TvShow>?) = true

            override suspend fun createCall() =
                remote.getOnAirTvShows()

            override suspend fun saveCallResult(data: TvResponse) {
                data.results?.toEntity()?.let {
                    local.saveTvShows(it)
                }
            }
        }.asFlow()
    }

    override fun getBookmarkedTvShows() = local.getBookmarkedTvShows().map { it.toDomain() }

    override fun getTv(tvShowId: String): Flow<Resource<TvShow>> {
        return object : NetworkBoundResource<TvShow, TvShowDto>() {

            override fun loadFromDB(): Flow<TvShow> {
                return local.getTvShow(tvShowId).map { it.toDomain() }
            }

            override fun shouldFetch(data: TvShow?) = data == null || !data.isFavourite

            override suspend fun createCall(): Flow<ApiResponse<TvShowDto>> {
                return remote.getTvShow(tvShowId)
            }

            override suspend fun saveCallResult(data: TvShowDto) {
                local.saveTvShow(data.toEntity())
            }
        }.asFlow()
    }

    override suspend fun setBookmarkedTvShow(tvShow: TvShow) = local.updateTvShow(tvShow.toEntity().apply {
        isFavourite = !isFavourite
    })

    override suspend fun setBookmarkedMovie(movie: Movie) = local.updateMovie(movie.toEntity().apply {
        isFavourite = !isFavourite
    })

}