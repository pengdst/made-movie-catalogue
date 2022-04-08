package io.github.pengdst.moviecatalogue.made.core.di

import androidx.room.Room
import io.github.pengdst.moviecatalogue.made.core.BuildConfig
import io.github.pengdst.moviecatalogue.made.core.data.repository.MovieRepository
import io.github.pengdst.moviecatalogue.made.core.data.source.local.MovieLocalSource
import io.github.pengdst.moviecatalogue.made.core.data.source.local.room.MovieRoomDatabase
import io.github.pengdst.moviecatalogue.made.core.data.source.remote.MovieRemoteSource
import io.github.pengdst.moviecatalogue.made.core.data.source.remote.retrofit.routes.MovieRoute
import io.github.pengdst.moviecatalogue.made.core.domain.repository.IMovieRepository
import io.github.pengdst.moviecatalogue.made.core.domain.usecase.MovieInteractor
import io.github.pengdst.moviecatalogue.made.core.domain.usecase.MovieUsecase
import io.github.pengdst.moviecatalogue.made.core.ui.SectionsPagerAdapter
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val databaseModule = module {

    factory { get<MovieRoomDatabase>().movieDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MovieRoomDatabase::class.java, "movie_database"
        ).fallbackToDestructiveMigration()
            .apply {
                val passphrase: ByteArray = SQLiteDatabase.getBytes("pengdst".toCharArray())
                openHelperFactory(SupportFactory(passphrase))
            }.build()
    }
}

val networkModule = module {

    factory {
        val hostname = "api.themoviedb.org"
        CertificatePinner.Builder()
            .add(hostname, "sha256/oD/WAoRPvbez1Y2dfYfuo4yujAcYHXdv1Ivb2v2MOKk=")
            .add(hostname, "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=")
            .add(hostname, "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=")
            .build()
    }

    single {
        OkHttpClient.Builder()
            .certificatePinner(get())
            .addInterceptor { chain ->
                val original: Request = chain.request()
                val originalHttpUrl: HttpUrl = original.url
                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("api_key", BuildConfig.API_KEY)
                    .build()

                val requestBuilder: Request.Builder = original.newBuilder()
                    .url(url)
                val request: Request = requestBuilder.build()
                chain.proceed(request)
            }.build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

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