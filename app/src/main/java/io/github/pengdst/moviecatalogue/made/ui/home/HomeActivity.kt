package io.github.pengdst.moviecatalogue.made.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import io.github.pengdst.moviecatalogue.made.core.domain.models.Section
import io.github.pengdst.moviecatalogue.made.ui.detail.DetailActivity
import io.github.pengdst.moviecatalogue.made.ui.favorite.FavoriteActivity
import io.github.pengdst.moviecatalogue.made.ui.home.sections.SectionsPagerAdapter
import io.github.pengdst.moviecatalogue.made.ui.home.sections.movie.MovieListFragment
import io.github.pengdst.moviecatalogue.made.ui.home.sections.tv.TvShowListFragment
import io.github.pengdst.libs.ui.activity.viewbinding.ActivityViewBindingDelegate.Companion.viewBindings
import io.github.pengdst.moviecatalogue.made.R
import io.github.pengdst.moviecatalogue.made.databinding.ActivityMainBinding
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), ContentCallback {

    private val binding: ActivityMainBinding by viewBindings()
    @Inject
    lateinit var sectionsPagerAdapter: SectionsPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        sectionsPagerAdapter.addSection(Section(getString(R.string.movie), MovieListFragment()))
        sectionsPagerAdapter.addSection(Section(getString(R.string.tv_show), TvShowListFragment()))

        binding.viewPager.adapter = sectionsPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_favorite -> {
                startActivity(Intent(this, FavoriteActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
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