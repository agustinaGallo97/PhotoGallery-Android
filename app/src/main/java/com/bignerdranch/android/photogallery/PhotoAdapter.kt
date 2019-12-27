package com.bignerdranch.android.photogallery

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PhotoAdapter(private val galleryItems: List<GalleryItem>) :
  RecyclerView.Adapter<PhotoAdapter.PhotoHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
    val textView = TextView(parent.context)
    return PhotoHolder(textView)
  }

  override fun getItemCount(): Int = galleryItems.size

  override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
    val galleryItem = galleryItems[position]
    holder.bindTitle(galleryItem.title)
  }

  inner class PhotoHolder(itemTextView: TextView) : RecyclerView.ViewHolder(itemTextView) {
    val bindTitle: (CharSequence) -> Unit = itemTextView::setText
  }
}
