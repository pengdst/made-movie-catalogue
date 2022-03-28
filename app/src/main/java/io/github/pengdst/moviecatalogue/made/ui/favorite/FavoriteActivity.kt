package io.github.pengdst.moviecatalogue.made.ui.favorite

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import io.github.pengdst.libs.ui.activity.viewbinding.ActivityViewBindingDelegate.Companion.viewBindings
import io.github.pengdst.moviecatalogue.made.R
import io.github.pengdst.moviecatalogue.made.databinding.ActivityFavoriteBinding
import io.github.pengdst.moviecatalogue.made.core.domain.models.Section
import io.github.pengdst.moviecatalogue.made.ui.detail.DetailActivity
import io.github.pengdst.moviecatalogue.made.ui.favorite.sections.movie.MovieFavoriteFragment
import io.github.pengdst.moviecatalogue.made.ui.favorite.sections.tv.TvShowFavoriteFragment
import io.github.pengdst.moviecatalogue.made.ui.home.ContentCallback
import io.github.pengdst.moviecatalogue.made.ui.home.sections.SectionsPagerAdapter
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity(), ContentCallback {

    private val binding: ActivityFavoriteBinding by viewBindings()
    @Inject
    lateinit var sectionsPagerAdapter: SectionsPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
}