package com.bignerdranch.android.photogallery

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PhotoGalleryFragment : Fragment(R.layout.fragment_photo_gallery) {
  companion object {
    private const val SPAN_COUNT = 3
    fun newInstance() = PhotoGalleryFragment()
  }

  private lateinit var photoRecyclerView: RecyclerView
  private lateinit var photoGalleryViewModel: PhotoGalleryViewModel
  private lateinit var thumbnailDownloader: ThumbnailDownloader<PhotoAdapter.PhotoHolder>

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    photoGalleryViewModel.galleryItemLiveData.observe(
      viewLifecycleOwner,
      Observer { galleryItems ->
        photoRecyclerView.adapter = PhotoAdapter(galleryItems, thumbnailDownloader)
      })

    viewLifecycleOwner.lifecycle.addObserver(thumbnailDownloader.viewLifecycleObserver)
    setUpPhotoRecyclerView(view)
  }

  private fun setUpPhotoRecyclerView(view: View) {
    photoRecyclerView = view.findViewById(R.id.photoRecyclerView)
    photoRecyclerView.layoutManager = GridLayoutManager(context, SPAN_COUNT)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    retainInstance = true
    photoGalleryViewModel = ViewModelProviders.of(this).get(PhotoGalleryViewModel::class.java)
    val responseHandler = Handler()
    thumbnailDownloader = ThumbnailDownloader(responseHandler) { photoHolder, bitmap ->
      val drawable = BitmapDrawable(resources, bitmap)
      photoHolder.bindDrawable(drawable)
    }
    lifecycle.addObserver(thumbnailDownloader.fragmentLifecycleObserver)
  }

  override fun onDestroyView() {
    super.onDestroyView()
    viewLifecycleOwner.lifecycle.removeObserver(thumbnailDownloader.viewLifecycleObserver)
  }

  override fun onDestroy() {
    super.onDestroy()
    lifecycle.removeObserver(thumbnailDownloader.fragmentLifecycleObserver)
  }
}
