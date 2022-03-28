package io.github.pengdst.moviecatalogue.made.ui.home.sections.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import io.github.pengdst.moviecatalogue.made.R
import io.github.pengdst.moviecatalogue.made.base.BaseListAdapter
import io.github.pengdst.moviecatalogue.made.core.domain.models.Movie
import io.github.pengdst.moviecatalogue.made.databinding.ItemMovieBinding
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Created on 5/11/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
class MovieListAdapter @Inject constructor()  : BaseListAdapter<Movie, MovieListAdapter.ViewHolder>(
    Movie.diffCallback) {

    class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val date = formatter.parse(movie.releaseDate)
            formatter.applyLocalizedPattern("EEEE, d MMMM yyyy")
            binding.tvMovieDate.text = formatter.format(date)
            binding.tvMovieTitle.text = movie.title
            binding.tvMovieDescription.text = movie.storyLine

            Glide.with(binding.root)
                .load(movie.imagePosterUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_baseline_broken_image_24)
                .transform(RoundedCorners(16))
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .override(100, 100)
                .centerCrop()
                .into(binding.ivMovieThumbnail)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { movie ->
            holder.bind(movie)
            holder.itemView.setOnClickListener {
                itemClickCallback?.onItemClick(it, movie, position)
            }
        }
    }
}