package io.github.pengdst.moviecatalogue.made.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Created on 5/19/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */

@HiltAndroidApp
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