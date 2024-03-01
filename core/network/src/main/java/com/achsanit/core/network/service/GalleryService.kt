package com.achsanit.core.network.service

import retrofit2.http.GET
import com.achsanit.core.model.networkresponse.PhotosResponseItem

interface GalleryService {

    @GET("photos")
    suspend fun getPhotos(): List<PhotosResponseItem>
}