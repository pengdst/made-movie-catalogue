package io.github.pengdst.moviecatalogue.favorite.di

import dagger.Component
import io.github.pengdst.moviecatalogue.favorite.ui.FavoriteActivity
import io.github.pengdst.moviecatalogue.made.core.di.ActivityDependencies

@Component(dependencies = [ActivityDependencies::class])
interface FavoriteComponent {

    fun inject(activity: FavoriteActivity)

    @Component.Factory
    interface Factory {
        fun create(loginModuleDependencies: ActivityDependencies): FavoriteComponent
    }
}