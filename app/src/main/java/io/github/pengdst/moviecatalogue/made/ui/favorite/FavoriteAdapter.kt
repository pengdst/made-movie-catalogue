package io.github.pengdst.moviecatalogue.made.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.pengdst.moviecatalogue.made.R
import io.github.pengdst.moviecatalogue.made.core.ui.BaseAdapter
import io.github.pengdst.moviecatalogue.made.databinding.ItemFavoriteBoxBinding
import javax.inject.Inject

/**
 * Created on 5/11/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
class FavoriteAdapter @Inject constructor() : BaseAdapter.ListAnimated<FavoriteData, FavoriteAdapter.ViewHolder>(
    FavoriteData.diffCallback) {

    class ViewHolder(private val binding: ItemFavoriteBoxBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: FavoriteData) {
            binding.title.text = tvShow.title

            Glide.with(binding.root)
                .load(tvShow.posterUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(binding.poster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFavoriteBoxBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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