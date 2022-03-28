package io.github.pengdst.moviecatalogue.made.ui.home.sections.tv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import io.github.pengdst.moviecatalogue.made.R
import io.github.pengdst.moviecatalogue.made.core.domain.models.TvShow
import io.github.pengdst.moviecatalogue.made.core.ui.BaseAdapter
import io.github.pengdst.moviecatalogue.made.databinding.ItemTvShowBinding
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
class TvShowListAdapter @Inject constructor() : BaseAdapter.ListAnimated<TvShow, TvShowListAdapter.ViewHolder>(
    TvShow.diffCallback) {

    class ViewHolder(private val binding: ItemTvShowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShow) {
            val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val date = formatter.parse(tvShow.releaseDate)
            formatter.applyLocalizedPattern("EEEE, d MMMM yyyy")
            binding.tvSeriesDate.text = formatter.format(date)
            binding.tvSeriesTitle.text = tvShow.title
            binding.tvSeriesDescription.text = tvShow.storyLine

            Glide.with(binding.root)
                .load(tvShow.imagePosterUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_baseline_broken_image_24)
                .transform(RoundedCorners(16))
                .override(100, 100)
                .centerCrop()
                .into(binding.ivSeriesThumbnail)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTvShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { tvShow ->
            holder.bind(tvShow)
            holder.itemView.setOnClickListener {
                itemClickCallback?.onItemClick(it, tvShow, position)
            }
        }
    }
}