package com.example.imageglimpse.view.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imageglimpse.databinding.ItemImagesBinding
import com.example.imageglimpse.model.ImageData

class MainAdapter(private val context:Context):RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    inner class MainViewHolder(val itemImagesBinding: ItemImagesBinding) :
        RecyclerView.ViewHolder(itemImagesBinding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<ImageData>() {
        override fun areItemsTheSame(oldItem: ImageData, newItem: ImageData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ImageData, newItem: ImageData): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, diffCallback)
    var images: List<ImageData>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.MainViewHolder {
        return MainViewHolder(
            ItemImagesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainAdapter.MainViewHolder, position: Int) {
        holder.itemImagesBinding.apply {
            val image = images[position]
            Glide.with(context)
                .load(image.url)
                .into(ivImage)

                tagline.text = image.title
            ibShare.setOnClickListener {
                shareBeer(image.url)
            }
        }
    }
    override fun getItemCount(): Int {
      return images.size
    }

    fun shareBeer(Image: String) {

        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"

        // Set the URL of the image as the content to be shared
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Checkout This Cool Beer")

        shareIntent.putExtra(Intent.EXTRA_TEXT, Image)

        // Start the activity for sharing
        context.startActivity(Intent.createChooser(shareIntent, "Share Image URL"))

    }
}