package com.example.amotherone.ui.theme



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.amotherone.R
import com.example.amotherone.data.ImgurImage
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ImgurImageAdapter(
    private val onClick: (ImgurImage) -> Unit
) : ListAdapter<ImgurImage, ImgurImageAdapter.ImageViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ImgurImage>() {
            override fun areItemsTheSame(oldItem: ImgurImage, newItem: ImgurImage): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ImgurImage, newItem: ImgurImage): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val image: ImageView = view.findViewById(R.id.imageView)
        private val date: TextView = view.findViewById(R.id.tvDate)

        fun bind(imgurImage: ImgurImage) {
            Glide.with(image.context).load(imgurImage.link).into(image)

            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val dateStr = sdf.format(Date(imgurImage.datetime * 1000))
            date.text = dateStr

            itemView.setOnClickListener { onClick(imgurImage) }
        }
    }
}
