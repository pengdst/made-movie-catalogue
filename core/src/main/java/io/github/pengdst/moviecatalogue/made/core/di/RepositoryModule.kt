package io.github.pengdst.moviecatalogue.made.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.pengdst.moviecatalogue.made.core.data.repository.MovieRepository
import io.github.pengdst.moviecatalogue.made.core.domain.repository.IMovieRepository
import javax.inject.Singleton

/**
 * Created on 6/1/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindMovieRepository(movieRepositoryImpl: MovieRepository): IMovieRepository

}