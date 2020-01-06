package com.bignerdranch.android.photogallery

import com.google.gson.annotations.SerializedName

data class GalleryItem(
    val title: String = "",
    val id: String = "",
    @SerializedName("url_s") var url: String = ""
)
