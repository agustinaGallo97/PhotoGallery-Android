package com.bignerdranch.android.photogallery

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class PhotoAdapter(
    private val galleryItems: List<GalleryItem>,
    private val thumbnailDownloader: ThumbnailDownloader<PhotoHolder>
) :
  RecyclerView.Adapter<PhotoAdapter.PhotoHolder>() {

  private var context: Context? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
    val view = LayoutInflater
      .from(parent.context)
      .inflate(R.layout.list_item_gallery, parent, false) as ImageView
    context = parent.context
    return PhotoHolder(view)
  }

  override fun getItemCount(): Int = galleryItems.size

  override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
    val galleryItem = galleryItems[position]
    val placeholder: Drawable = ContextCompat.getDrawable(
      context!!,
      R.drawable.bill_up_close
    ) ?: ColorDrawable()
    holder.bindDrawable(placeholder)
    thumbnailDownloader.queueThumbnail(holder, galleryItem.url)
  }

  inner class PhotoHolder(itemImageView: ImageView) : RecyclerView.ViewHolder(itemImageView) {
    val bindDrawable: (Drawable) -> Unit = itemImageView::setImageDrawable
  }
}
