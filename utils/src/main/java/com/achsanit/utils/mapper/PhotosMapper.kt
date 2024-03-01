package com.achsanit.utils.mapper

import com.achsanit.core.model.PhotoItemEntity
import com.achsanit.core.model.networkresponse.PhotosResponseItem

// extension mapping from list PhotosResponse Network to list photo item entity
fun List<PhotosResponseItem>.map(): List<PhotoItemEntity> {
    return this.map {
        PhotoItemEntity(
            photoUrl = it.urls?.regular ?: "",
            photoUrlFull = it.urls?.full ?: ""
        )
    }
}