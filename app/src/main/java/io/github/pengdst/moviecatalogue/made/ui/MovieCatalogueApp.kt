package io.github.pengdst.moviecatalogue.made.ui

import android.app.Application
import io.github.pengdst.moviecatalogue.made.core.di.*
import io.github.pengdst.moviecatalogue.made.di.appModule
import io.github.pengdst.moviecatalogue.made.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

/**
 * Created on 5/19/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */

@Suppress("unused")
class MovieCatalogueApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MovieCatalogueApp)
            fragmentFactory()
            modules(
                listOf(
                    appModule,
                    coreModule,
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule,
                )
            )
        }
    }
}