package io.github.pengdst.moviecatalogue.made.core.di

import androidx.fragment.app.FragmentActivity
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@EntryPoint
@InstallIn(ActivityComponent::class)
interface ActivityDependencies {
    fun provideFragmentActivity(): FragmentActivity
}