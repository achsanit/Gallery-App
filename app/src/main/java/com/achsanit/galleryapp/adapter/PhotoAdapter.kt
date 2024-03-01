package com.achsanit.galleryapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.achsanit.core.model.PhotoItemEntity
import com.achsanit.galleryapp.databinding.ItemPhotoBinding
import com.bumptech.glide.Glide

class PhotoAdapter(private val onItemClick: (data: PhotoItemEntity) -> Unit): RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {
    inner class ViewHolder(
        private val binding: ItemPhotoBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(data: PhotoItemEntity) {
            binding.ivPhoto.setOnClickListener {
                onItemClick(data)
            }

            Glide.with(this.itemView.context)
                .load(data.photoUrl)
                .into(binding.ivPhoto)
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<PhotoItemEntity>() {
        override fun areItemsTheSame(oldItem: PhotoItemEntity, newItem: PhotoItemEntity): Boolean {
            return oldItem.photoUrl == newItem.photoUrl
        }

        override fun areContentsTheSame(oldItem: PhotoItemEntity, newItem: PhotoItemEntity): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    fun setData(data: List<PhotoItemEntity>) = differ.submitList(data)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item)
    }
}