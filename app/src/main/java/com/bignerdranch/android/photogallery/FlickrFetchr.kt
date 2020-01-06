package com.bignerdranch.android.photogallery

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bignerdranch.android.photogallery.api.FlickrApi
import com.bignerdranch.android.photogallery.api.FlickrResponse
import com.bignerdranch.android.photogallery.api.PhotoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

class FlickrFetchr {
  companion object {
    private const val TAG = "PhotoGalleryFragment"
    @StringRes
    private const val BASE_URL = R.string.base_url
  }

  private val flickrApi: FlickrApi

  init {
    val retrofit: Retrofit =
    Retrofit.Builder()
      .baseUrl(PhotoGalleryApplication.context.resources.getString(BASE_URL))
      .addConverterFactory(GsonConverterFactory.create())
      .build()

    flickrApi = retrofit.create(FlickrApi::class.java)
  }

  fun fetchPhotos(): LiveData<List<GalleryItem>> {
    val responseLiveData: MutableLiveData<List<GalleryItem>> = MutableLiveData()
    val flickrRequest: Call<FlickrResponse> = flickrApi.fetchPhotos()

    flickrRequest.enqueue(object : Callback<FlickrResponse> {

      override fun onFailure(call: Call<FlickrResponse>, t: Throwable) {
        Timber.d(t, "Failed to fetch photos")
      }

      override fun onResponse(call: Call<FlickrResponse>, response: Response<FlickrResponse>) {
        Timber.d("Response received: ${response.body()}")

        val flickrResponse: FlickrResponse? = response.body()
        val photoResponse: PhotoResponse? = flickrResponse?.photos
        var galleryItems: List<GalleryItem> = photoResponse?.galleryItems ?: mutableListOf()
        galleryItems = galleryItems.filterNot { it.url.isBlank() }
        responseLiveData.value = galleryItems
      }
    })
    return responseLiveData
  }
}
