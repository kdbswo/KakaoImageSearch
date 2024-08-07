package com.loci.kakaoimagesearch.presentation.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.loci.kakaoimagesearch.R
import com.loci.kakaoimagesearch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)


        initNavigationBar()
        binding.bottomNavigationBar.selectedItemId = R.id.homeItem
        setFragment(SearchFragment())


    }

    private fun initNavigationBar() {
        binding.bottomNavigationBar.run {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.homeItem -> {
                        setFragment(SearchFragment())
                        true
                    }

                    R.id.galleryItem -> {
                        setFragment(GalleryFragment())
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


}