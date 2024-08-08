package com.loci.kakaoimagesearch.presentation.main

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.loci.kakaoimagesearch.R
import com.loci.kakaoimagesearch.databinding.ActivityMainBinding
import com.loci.kakaoimagesearch.util.jsonListToString

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val galleryViewModel by viewModels<GalleryViewModel> {
        GalleryViewModelFactory(this@MainActivity)
    }

    private val searchFragment = SearchFragment()
    private val galleryFragment = GalleryFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        galleryViewModel.loadGalleryList(this@MainActivity)
        initNavigationBar()
        binding.bottomNavigationBar.selectedItemId = R.id.homeItem
        setFragment(SearchFragment())

    }

    private fun initNavigationBar() {
        binding.bottomNavigationBar.run {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.homeItem -> {
                        setFragment(searchFragment)
                        true
                    }

                    R.id.galleryItem -> {
                        setFragment(galleryFragment)
                        true
                    }

                    else -> false
                }
            }
        }
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame_layout, fragment).commit()
    }

    override fun onStop() {
        saveData()
        super.onStop()
    }

    private fun saveData() {
        val pref = getSharedPreferences("pref", 0)
        val edit = pref.edit()
        val stringList = jsonListToString(galleryViewModel.galleryList)
        Log.d("end", stringList)
        edit.putString("gallery", stringList)
        edit.apply()
    }


}