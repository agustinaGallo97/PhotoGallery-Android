package com.bignerdranch.android.photogallery.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface FlickrApi {
  companion object {
    private const val SERVICE_METHOD = "flickr.interestingness.getList"
    private const val API_KEY = "4b5b2b9bbf978d6f98fda8be525493d0"
    private const val FORMAT = "json"
    private const val JSON_CALLBACK = "1"
    private const val EXTRAS = "url_s"
  }

  @GET(
    "services/rest/?method=$SERVICE_METHOD" +
        "&api_key=$API_KEY" +
        "&format=$FORMAT" +
        "&nojsoncallback=$JSON_CALLBACK" +
        "&extras=$EXTRAS"
  )
  fun fetchPhotos(): Call<FlickrResponse>

  @GET
  fun fetchUrlBytes(@Url url: String): Call<ResponseBody>
}
