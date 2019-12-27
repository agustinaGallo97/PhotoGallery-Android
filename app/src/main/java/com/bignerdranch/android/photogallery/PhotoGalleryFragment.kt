package com.bignerdranch.android.photogallery

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PhotoGalleryFragment : Fragment(R.layout.fragment_photo_gallery) {
  companion object {
    fun newInstance() = PhotoGalleryFragment()
  }

  private lateinit var photoRecyclerView: RecyclerView
  private lateinit var photoGalleryViewModel: PhotoGalleryViewModel

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    photoGalleryViewModel.galleryItemLiveData.observe(
      viewLifecycleOwner,
      Observer { galleryItems ->
        photoRecyclerView.adapter = PhotoAdapter(galleryItems)
      })

    setUpPhotoRecyclerView(view)
  }

  private fun setUpPhotoRecyclerView(view: View) {
    photoRecyclerView = view.findViewById(R.id.photoRecyclerView)
    photoRecyclerView.layoutManager = GridLayoutManager(context, 3)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    photoGalleryViewModel = ViewModelProviders.of(this).get(PhotoGalleryViewModel::class.java)
  }
}
