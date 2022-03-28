package io.github.pengdst.moviecatalogue.made.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import io.github.pengdst.moviecatalogue.made.core.domain.usecase.MovieInteractor
import io.github.pengdst.moviecatalogue.made.core.domain.usecase.MovieUsecase

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {

    @Binds
    @ViewModelScoped
    abstract fun provideMovieUsecase(movieInteractor: MovieInteractor): MovieUsecase
}