package io.github.pengdst.moviecatalogue.made.core.ui

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter {

    interface Callback {
        interface OnItemClick<Data> {
            fun onItemClick(view: View, data: Data, position: Int)
        }
    }

    abstract class ListAnimated<Data, VH : RecyclerView.ViewHolder>(
        diffItemCallback: DiffUtil.ItemCallback<Data>
    ) : ListAdapter<Data, VH>(diffItemCallback) {

        protected var itemClickCallback: Callback.OnItemClick<Data>? = null

        fun submitData(list: List<Data>) = submitList(list)

        fun setOnItemClickListener(onClick: (View, Data, Int) -> Unit) {
            itemClickCallback = object : Callback.OnItemClick<Data> {
                override fun onItemClick(view: View, data: Data, position: Int) {
                    onClick(view, data, position)
                }
            }
        }
    }

}