package com.idrive.galleryphotos.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.idrive.galleryphotos.R
import com.idrive.galleryphotos.model.MediaStoreImage

class GalleryAdapter(private val onClick: (MediaStoreImage,Int) -> Unit) :
    ListAdapter<MediaStoreImage, GalleryAdapter.ImageViewHolder>(MediaStoreImage.DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.gallery_layout, parent, false)
        return ImageViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val mediaStoreImage = getItem(position)
        holder.rootView.tag = mediaStoreImage
        holder.imageView.setOnClickListener {
            val image = holder.rootView.tag as? MediaStoreImage ?: return@setOnClickListener
            onClick(image,position)
        }
        Glide.with(holder.imageView)
            .load(mediaStoreImage.contentUri)
            .thumbnail(0.33f)
            .centerCrop()
            .into(holder.imageView)
    }


    inner class ImageViewHolder(view: View, onClick: (MediaStoreImage,Int) -> Unit) :
        RecyclerView.ViewHolder(view) {
        val rootView = view
        val imageView: ImageView = view.findViewById(R.id.image)

        /* init {
             imageView.setOnClickListener {
                 val image = rootView.tag as? MediaStoreImage ?: return@setOnClickListener
                 onClick(image)
             }
         }*/
    }
}