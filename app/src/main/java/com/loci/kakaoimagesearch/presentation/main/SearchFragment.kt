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
import com.loci.kakaoimagesearch.util.convertStringToSearchImageEntity
import com.loci.kakaoimagesearch.util.jsonListToString


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

    private lateinit var query: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        galleryViewModel.loadGalleryList(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvMainSearchList.adapter = searchListViewAdapter
        binding.rvMainSearchList.layoutManager = GridLayoutManager(this.context, 2)

        binding.ctSearchButton.setOnClickListener {
            query = binding.etSearch.text.toString()
            searchViewModel.getSearchImageList(query)
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

        Log.d("gallery",galleryViewModel.galleryList.value.toString())

    }

    override fun onDestroyView() {
        _binding = null
//        saveData()
        super.onDestroyView()
    }

//    private fun saveData() {
//        val pref = requireContext().getSharedPreferences("pref", 0)
//        val edit = pref.edit()
//        val stringList = jsonListToString(galleryViewModel.galleryList)
//        edit.putString("gallery", stringList)
//        edit.apply()
//    }

}