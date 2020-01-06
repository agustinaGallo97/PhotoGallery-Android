package com.bignerdranch.android.photogallery

import android.app.Application

class PhotoGalleryApplication : Application() {
  companion object {
    lateinit var context: PhotoGalleryApplication
  }

  override fun onCreate() {
    super.onCreate()
    context = this
  }
}
