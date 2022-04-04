package io.github.pengdst.moviecatalogue.favorite.ui.sections.tv

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import io.github.pengdst.libs.ui.fragment.viewbinding.FragmentViewBindingDelegate.Companion.viewBindings
import io.github.pengdst.moviecatalogue.favorite.ui.FavoriteAdapter
import io.github.pengdst.moviecatalogue.favorite.ui.FavoriteViewModel
import io.github.pengdst.moviecatalogue.made.databinding.FragmentTvShowListBinding
import io.github.pengdst.moviecatalogue.made.ui.home.ContentCallback
import io.github.pengdst.moviecatalogue.made.utils.DataStore
import org.koin.android.ext.android.inject

class TvShowFavoriteFragment : Fragment() {

    private val binding: FragmentTvShowListBinding by viewBindings()
    private val viewModel: FavoriteViewModel by inject()
    private val favoriteAdapter: FavoriteAdapter by inject()
    private var contentCallback: ContentCallback? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        contentCallback = context as ContentCallback
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        viewModel.getFavoriteData(DataStore.TYPE_TV_SHOW).observe(viewLifecycleOwner){
            showLoading(false)
            favoriteAdapter.submitData(it)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.ltLoading.isVisible = isLoading
    }

    private fun setupRecyclerView(){
        favoriteAdapter.setOnItemClickListener { _, tv, position ->
            contentCallback?.moveTo(position, tv.id, tv.type)
        }
        binding.rvTvShows.apply {
            adapter = favoriteAdapter
            layoutManager = StaggeredGridLayoutManager(3, RecyclerView.VERTICAL)
        }
    }
}