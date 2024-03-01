package com.achsanit.core.data

import com.achsanit.core.model.PhotoItemEntity
import com.achsanit.core.network.service.GalleryService
import com.achsanit.utils.Resource
import com.achsanit.utils.mapper.map
import com.achsanit.utils.mapper.resourceMapper
import javax.inject.Inject

class Repository @Inject constructor(private val galleryService: GalleryService) {

    suspend fun getPhotos(): Resource<List<PhotoItemEntity>> {
        return resourceMapper { galleryService.getPhotos().map() }
    }
}