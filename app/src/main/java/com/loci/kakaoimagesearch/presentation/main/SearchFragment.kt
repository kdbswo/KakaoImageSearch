package com.loci.kakaoimagesearch.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.loci.kakaoimagesearch.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val searchListViewAdapter by lazy { SearchListViewAdapter() }

    private val searchViewModel by viewModels<SearchViewModel> {
        SearchViewModelFactory()
    }

    private val galleryViewModel by activityViewModels<GalleryViewModel> {
        GalleryViewModelFactory(requireActivity())
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvMainSearchList.adapter = searchListViewAdapter
        binding.rvMainSearchList.layoutManager = GridLayoutManager(this.context, 2)

        binding.ctSearchButton.setOnClickListener {
            val query = binding.etSearch.text.toString()
            searchViewModel.getSearchImageList(query)
            Log.d("q", query)
        }

        searchListViewAdapter.setItemClickListener(object :
            SearchListViewAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                val searchItem = searchViewModel.returnSearchItem(position)
                searchItem?.let { galleryViewModel.addGalleryList(it) }

            }

        })

        searchViewModel.getSearchImageList.observe(viewLifecycleOwner) { searchImageList ->
            searchListViewAdapter.submitList(searchImageList)
        }

    }

    override fun onDestroyView() {
        _binding = null

        super.onDestroyView()
    }


}