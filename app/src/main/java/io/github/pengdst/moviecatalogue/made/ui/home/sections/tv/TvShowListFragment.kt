package io.github.pengdst.moviecatalogue.made.ui.home.sections.tv

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.pengdst.libs.ui.fragment.viewbinding.FragmentViewBindingDelegate.Companion.viewBindings
import io.github.pengdst.moviecatalogue.made.core.data.vo.Resource
import io.github.pengdst.moviecatalogue.made.databinding.FragmentTvShowListBinding
import io.github.pengdst.moviecatalogue.made.ui.home.ContentCallback
import io.github.pengdst.moviecatalogue.made.ui.home.HomeViewModel
import io.github.pengdst.moviecatalogue.made.utils.DataStore
import io.github.pengdst.moviecatalogue.made.core.utils.longToast
import javax.inject.Inject

@AndroidEntryPoint
class TvShowListFragment : Fragment() {

    private val binding: FragmentTvShowListBinding by viewBindings()
    private val viewModel: HomeViewModel by viewModels()
    @Inject lateinit var tvShowListAdapter: TvShowListAdapter
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
        viewModel.getTvShowList().observe(viewLifecycleOwner){
            showLoading(false)
            when(it){
                is Resource.Success -> tvShowListAdapter.submitData(it.data)
                is Resource.Error -> context.longToast(it.message)
                is Resource.Loading -> showLoading(true)
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.ltLoading.isVisible = isLoading
    }

    private fun setupRecyclerView(){
        tvShowListAdapter.setOnItemClickListener { _, tv, position ->
            contentCallback?.moveTo(position, tv.id, DataStore.TYPE_TV_SHOW)
        }
        binding.rvTvShows.adapter = tvShowListAdapter
    }
}