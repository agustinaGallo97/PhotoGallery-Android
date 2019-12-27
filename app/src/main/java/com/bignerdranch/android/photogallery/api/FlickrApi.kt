package com.bignerdranch.android.photogallery.api

import retrofit2.Call
import retrofit2.http.GET

interface FlickrApi {
  @GET(
    "services/rest/?method = flickr.interestingness.getList" +
        "&api_key = 4b5b2b9bbf978d6f98fda8be525493d0" +
        "&format = json" +
        "&nojsoncallback = 1" +
        "&extras = url_s"
  )
  fun fetchPhotos(): Call<FlickrResponse>
}
