package io.github.pengdst.moviecatalogue.favorite.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.EntryPoints
import io.github.pengdst.libs.ui.activity.viewbinding.ActivityViewBindingDelegate.Companion.viewBindings
import io.github.pengdst.moviecatalogue.favorite.di.DaggerFavoriteComponent
import io.github.pengdst.moviecatalogue.favorite.di.favoriteModule
import io.github.pengdst.moviecatalogue.made.R
import io.github.pengdst.moviecatalogue.made.core.di.ActivityDependencies
import io.github.pengdst.moviecatalogue.made.core.domain.models.Section
import io.github.pengdst.moviecatalogue.made.core.ui.SectionsPagerAdapter
import io.github.pengdst.moviecatalogue.made.databinding.ActivityFavoriteBinding
import io.github.pengdst.moviecatalogue.made.di.appModule
import io.github.pengdst.moviecatalogue.made.ui.detail.DetailActivity
import io.github.pengdst.moviecatalogue.made.ui.favorite.sections.movie.MovieFavoriteFragment
import io.github.pengdst.moviecatalogue.made.ui.favorite.sections.tv.TvShowFavoriteFragment
import io.github.pengdst.moviecatalogue.made.ui.home.ContentCallback
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules
import org.koin.core.parameter.parametersOf

class FavoriteActivity : AppCompatActivity(), ContentCallback {

    private val binding: ActivityFavoriteBinding by viewBindings()
    private val sectionsPagerAdapter: SectionsPagerAdapter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(listOf(favoriteModule, appModule))
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        sectionsPagerAdapter.addSection(Section(getString(R.string.movie), MovieFavoriteFragment()))
        sectionsPagerAdapter.addSection(Section(getString(R.string.tv_show), TvShowFavoriteFragment()))

        binding.viewPager.adapter = sectionsPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)
    }

    override fun moveTo(position: Int, contentId: String, contentType: String) {
        Intent(applicationContext, DetailActivity::class.java).apply {
            putExtras(Bundle().apply {
                putInt(DetailActivity.EXTRA_CONTENT_POSITION, position)
                putString(DetailActivity.EXTRA_CONTENT_ID, contentId)
                putString(DetailActivity.EXTRA_CONTENT_TYPE, contentType)
            })
        }.also {
            startActivity(it)
        }
    }

    fun initDependencies() {

        val dynamicModulesDependencies = EntryPoints.get(
            applicationContext,
            ActivityDependencies::class.java
        )

        DaggerFavoriteComponent.factory()
            .create(dynamicModulesDependencies)
            .inject(this)
    }
}