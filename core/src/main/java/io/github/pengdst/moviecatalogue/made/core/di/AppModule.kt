package io.github.pengdst.moviecatalogue.made.core.di

import io.github.pengdst.moviecatalogue.made.core.data.repository.MovieRepository
import io.github.pengdst.moviecatalogue.made.core.data.source.local.MovieLocalSource
import io.github.pengdst.moviecatalogue.made.core.data.source.local.room.MovieRoomDatabase
import io.github.pengdst.moviecatalogue.made.core.data.source.remote.MovieRemoteSource
import io.github.pengdst.moviecatalogue.made.core.data.source.remote.retrofit.RetrofitBuilder
import io.github.pengdst.moviecatalogue.made.core.data.source.remote.retrofit.routes.MovieRoute
import io.github.pengdst.moviecatalogue.made.core.domain.repository.IMovieRepository
import io.github.pengdst.moviecatalogue.made.core.domain.usecase.MovieInteractor
import io.github.pengdst.moviecatalogue.made.core.domain.usecase.MovieUsecase
import io.github.pengdst.moviecatalogue.made.core.ui.SectionsPagerAdapter
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit

val databaseModule = module {

    factory { get<MovieRoomDatabase>().movieDao() }
    single { MovieRoomDatabase.newInstance(androidContext()) }

}

val networkModule = module {

    single { RetrofitBuilder.build() }

    single { get<Retrofit>().create(MovieRoute::class.java) }
}

val repositoryModule = module {

    single { MovieLocalSource(get()) }

    single { MovieRemoteSource(get()) }

    single<IMovieRepository> { MovieRepository(get(), get()) }
}

val useCaseModule = module {
    factory<MovieUsecase> { MovieInteractor(get()) }
}

val coreModule = module {
    factory { params ->
        SectionsPagerAdapter(params.get())
    }
}