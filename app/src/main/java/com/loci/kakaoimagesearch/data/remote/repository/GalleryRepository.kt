package com.loci.kakaoimagesearch.data.remote.repository

import com.loci.kakaoimagesearch.data.remote.model.SearchImageListEntity

interface GalleryRepository {
//    fun addGalleryItem(item: SearchImageEntity)
    fun loadGalleryList(): SearchImageListEntity?
}