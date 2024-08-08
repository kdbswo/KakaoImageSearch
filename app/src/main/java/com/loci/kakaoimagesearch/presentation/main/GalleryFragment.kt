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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("onCreateG", galleryViewModel.galleryList.toString())

    }

    override fun onStart() {
        super.onStart()
        Log.d("onStartG", galleryViewModel.galleryList.toString())

    }

    override fun onResume() {
        Log.d("onResumeG", galleryViewModel.galleryList.toString())
        super.onResume()
    }

    override fun onPause() {
        Log.d("onPauseG", galleryViewModel.galleryList.toString())
        super.onPause()
    }

    override fun onStop() {
        Log.d("onStopG", galleryViewModel.galleryList.toString())
        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d("onSaveInstanceStateG", galleryViewModel.galleryList.toString())
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        Log.d("onDestroyG", galleryViewModel.galleryList.toString())
        super.onDestroy()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        Log.d("onCreateViewG", galleryViewModel.galleryList.toString())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvGalleryList.adapter = galleryListViewAdapter
        binding.rvGalleryList.layoutManager = GridLayoutManager(this.context, 2)


        galleryViewModel.galleryList.observe(viewLifecycleOwner) { galleryList ->
            galleryListViewAdapter.submitList(galleryList)
            Log.d("converG", galleryList.toString())

        }

        Log.d("onViewCreatedG", galleryViewModel.galleryList.toString())


    }

    override fun onDestroyView() {
        _binding = null
        Log.d("onDestroyViewG", galleryViewModel.galleryList.toString())

        super.onDestroyView()

    }

    private fun loadData() {
        val pref = requireContext().getSharedPreferences("pref", 0)
        val list = pref.getString("gallery", "[]")
        val convertedList = list?.let { convertStringToSearchImageEntity(it) }
        convertedList?.let { galleryViewModel.setGalleryList(it) }
        Log.d("conG",convertedList.toString())
    }



}