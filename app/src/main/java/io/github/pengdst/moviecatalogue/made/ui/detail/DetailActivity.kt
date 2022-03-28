package io.github.pengdst.moviecatalogue.made.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import dagger.hilt.android.AndroidEntryPoint
import io.github.pengdst.libs.ui.activity.viewbinding.ActivityViewBindingDelegate.Companion.viewBindings
import io.github.pengdst.moviecatalogue.made.R
import io.github.pengdst.moviecatalogue.made.core.data.vo.Resource
import io.github.pengdst.moviecatalogue.made.core.domain.models.Movie
import io.github.pengdst.moviecatalogue.made.core.domain.models.TvShow
import io.github.pengdst.moviecatalogue.made.databinding.ActivityDetailBinding
import io.github.pengdst.moviecatalogue.made.utils.DataStore
import io.github.pengdst.moviecatalogue.made.utils.longToast

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_CONTENT_TYPE = "content_type"
        const val EXTRA_CONTENT_POSITION = "content_position"
        const val EXTRA_CONTENT_ID = "content_id"
    }

    private var extras: Bundle? = null
    private val binding: ActivityDetailBinding by viewBindings()
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        extras = intent.extras
        viewModel.setSelectedContent(extras?.getString(EXTRA_CONTENT_ID))

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onResume() {
        super.onResume()

        when (extras?.getString(EXTRA_CONTENT_TYPE)) {
            DataStore.TYPE_MOVIE -> viewModel.getMovie().observe(this){ resource ->
                showLoading(false)
                when(resource){
                    is Resource.Success -> {
                        binding.fabFavourite.setOnClickListener {
                            viewModel.setBookmark(resource.data)
                        }
                        populateDetail(resource.data)
                    }
                    is Resource.Error -> longToast(resource.message)
                    is Resource.Loading -> showLoading(true)
                }
            }
            DataStore.TYPE_TV_SHOW -> viewModel.getTvShow().observe(this){ resource ->
                showLoading(false)
                when(resource){
                    is Resource.Success -> {
                        binding.fabFavourite.setOnClickListener {
                            viewModel.setBookmark(resource.data)
                        }
                        populateDetail(mapFromTvShow(resource.data))
                    }
                    is Resource.Error -> longToast(resource.message)
                    is Resource.Loading -> showLoading(true)
                }
            }
            else -> Unit
        }

    }

    private fun showLoading(isLoading: Boolean) {
        binding.ltLoading.isVisible = isLoading
    }

    private fun populateDetail(movie: Movie) {
        with(binding) {
            toolbar.title = movie.title
            tvTitle.text = movie.title
            tvGenre.text = movie.genre
            tvLanguage.text = movie.language
            tvReleaseDate.text = movie.releaseDate
            tvStoryline.text = movie.storyLine
            fabFavourite.setImageResource(if (movie.isFavourite) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24)

            Glide.with(applicationContext)
                .load(movie.imageBackdropUrl)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(ivBackdrop)

            Glide.with(applicationContext)
                .load(movie.imagePosterUrl)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(ivThumbnail)
        }
    }

    private fun mapFromTvShow(tvShow: TvShow) = Movie(
        id = tvShow.id,
        title = tvShow.title,
        genre = tvShow.genre,
        language = tvShow.language,
        releaseDate = tvShow.releaseDate,
        storyLine = tvShow.storyLine,
        posterPath = tvShow.posterPath,
        backdropPath = tvShow.backdropPath,
        isFavourite = tvShow.isFavourite,
        createdAt = tvShow.createdAt,
        updatedAt = tvShow.updatedAt
    )

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}