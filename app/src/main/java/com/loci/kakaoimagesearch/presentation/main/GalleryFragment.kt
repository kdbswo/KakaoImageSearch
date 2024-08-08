package com.loci.kakaoimagesearch.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.loci.kakaoimagesearch.data.remote.model.SearchImageEntity
import com.loci.kakaoimagesearch.databinding.FragmentGalleryBinding
import com.loci.kakaoimagesearch.util.convertStringToSearchImageEntity
import com.loci.kakaoimagesearch.util.jsonListToString
import java.util.Date


class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    private val galleryViewModel by activityViewModels<GalleryViewModel> {
        GalleryViewModelFactory(requireActivity())
    }

    private val galleryListViewAdapter by lazy { SearchListViewAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvGalleryList.adapter = galleryListViewAdapter
        binding.rvGalleryList.layoutManager = GridLayoutManager(this.context, 2)



        galleryViewModel.galleryList.observe(viewLifecycleOwner) { galleryList ->
            galleryListViewAdapter.submitList(galleryList)
            Log.d("conver", galleryList.toString())

        }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()

    }

    private fun loadData() {
        val pref = requireContext().getSharedPreferences("pref", 0)
        val list = pref.getString("gallery", "[]")
        val convertedList = list?.let { convertStringToSearchImageEntity(it) }
        convertedList?.let { galleryViewModel.setGalleryList(it) }
        Log.d("con",convertedList.toString())
    }



}