package io.github.pengdst.moviecatalogue.made.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import io.github.pengdst.libs.ui.activity.viewbinding.ActivityViewBindingDelegate.Companion.viewBindings
import io.github.pengdst.moviecatalogue.made.R
import io.github.pengdst.moviecatalogue.made.core.domain.models.Section
import io.github.pengdst.moviecatalogue.made.core.ui.SectionsPagerAdapter
import io.github.pengdst.moviecatalogue.made.databinding.ActivityMainBinding
import io.github.pengdst.moviecatalogue.made.ui.detail.DetailActivity
import io.github.pengdst.moviecatalogue.made.ui.home.sections.movie.MovieListFragment
import io.github.pengdst.moviecatalogue.made.ui.home.sections.tv.TvShowListFragment
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class HomeActivity : AppCompatActivity(), ContentCallback {

    private val binding: ActivityMainBinding by viewBindings()
    private val sectionsPagerAdapter: SectionsPagerAdapter by inject { parametersOf(this) }

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
                val uri = Uri.parse("moviecatalogue://favorite")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
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