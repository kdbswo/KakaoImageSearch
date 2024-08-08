package com.loci.kakaoimagesearch.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.loci.kakaoimagesearch.data.remote.model.SearchImageEntity
import com.loci.kakaoimagesearch.databinding.FragmentGalleryBinding


class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    private val galleryViewModel by viewModels<GalleryViewModel> {
        GalleryViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()

    }


    override fun onDestroyView() {
        saveData()
        _binding = null
        super.onDestroyView()
    }

    private fun saveData() {
        val pref = requireContext().getSharedPreferences("pref", 0)
        val edit = pref.edit()
        val stringList = jsonListToString(galleryViewModel.galleryList)
        edit.putString("gallery", stringList)
        edit.apply()
    }

    private fun loadData(){
        val pref = requireContext().getSharedPreferences("pref", 0)
        val list = pref.getString("gallery","")
        val convertedList = list?.let { convertToSearchImageEntity(it) }
        convertedList?.let { galleryViewModel.setGalleryList(it) }
    }

    private fun convertToSearchImageEntity(data: String): List<SearchImageEntity>? {
        val gson = Gson()
        val itemType = object : TypeToken<List<SearchImageEntity>>() {}.type
        return gson.fromJson<List<SearchImageEntity>>(data, itemType)
    }

    private fun jsonListToString(liveData: LiveData<List<SearchImageEntity>>): String {
        val gson = Gson()
        return gson.toJson(liveData)
    }


}