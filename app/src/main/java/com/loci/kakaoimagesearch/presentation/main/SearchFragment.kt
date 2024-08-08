package com.loci.kakaoimagesearch.presentation.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.loci.kakaoimagesearch.databinding.FragmentSearchBinding
import com.loci.kakaoimagesearch.util.convertStringToSearchImageEntity


class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val searchListViewAdapter by lazy { SearchListViewAdapter() }

    private val searchViewModel by activityViewModels<SearchViewModel> {
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
        binding.rvMainSearchList.adapter = searchListViewAdapter
        binding.rvMainSearchList.layoutManager = GridLayoutManager(this.context, 2)

        binding.ctSearchButton.setOnClickListener {
            val query = binding.etSearch.text.toString()
            searchViewModel.getSearchImageList(query)
            Log.d("q", query)
            downKeyboard(requireActivity(), binding.etSearch)
        }

        binding.etSearch.setText(loadQuery())
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchListViewAdapter.setItemClickListener(object :
            SearchListViewAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                val searchItem = searchViewModel.returnSearchItem(position)
                searchItem?.let { galleryViewModel.addGalleryList(it) }

                searchViewModel.updateIsLike(position)


            }

        })

        searchViewModel.getSearchImageList.observe(viewLifecycleOwner) { searchImageList ->
            searchListViewAdapter.submitList(searchImageList)
            Log.d("list", searchImageList.toString())
        }
    }

    override fun onPause() {
        saveQuery(binding.etSearch.text.toString())

        super.onPause()
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun downKeyboard(context: Context, et: EditText) {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        et.clearFocus()
        inputMethodManager.hideSoftInputFromWindow(et.windowToken, 0)
    }

    private fun saveQuery(query: String) {
        val pref = requireContext().getSharedPreferences("pref", 0)
        val edit = pref.edit()
        edit.putString("query", query)
        edit.apply()

    }

    private fun loadQuery(): String? {
        val pref = requireContext().getSharedPreferences("pref", 0)
        return pref.getString("query", "")
    }

    private fun loadData() {
        val pref = requireContext().getSharedPreferences("pref", 0)
        val list = pref.getString("gallery", "[]")
        val convertedList = list?.let { convertStringToSearchImageEntity(it) }
        convertedList?.let { galleryViewModel.setGalleryList(it) }
    }


}