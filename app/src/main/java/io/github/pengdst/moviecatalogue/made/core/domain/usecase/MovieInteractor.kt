package io.github.pengdst.moviecatalogue.made.core.domain.usecase

import io.github.pengdst.moviecatalogue.made.core.domain.models.Movie
import io.github.pengdst.moviecatalogue.made.core.domain.models.TvShow
import io.github.pengdst.moviecatalogue.made.core.domain.repository.IMovieRepository
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val movieRepository: IMovieRepository) : MovieUsecase {
    override fun getUpcomingMovies() = movieRepository.getUpcomingMovies()

    override fun getOnAirTvShows() = movieRepository.getOnAirTvShows()

    override fun getBookmarkedMovies() = movieRepository.getBookmarkedMovies()

    override fun getBookmarkedTvShows() = movieRepository.getBookmarkedTvShows()

    override fun getDetailMovie(movieId: String) = movieRepository.getMovie(movieId)

    override fun getDetailTvShow(tvShowId: String) = movieRepository.getTv(tvShowId)

    override suspend fun setFavouriteMovie(movie: Movie) = movieRepository.setBookmarkedMovie(movie)

    override suspend fun setFavouriteTvShow(tvShow: TvShow) = movieRepository.setBookmarkedTvShow(tvShow)

}