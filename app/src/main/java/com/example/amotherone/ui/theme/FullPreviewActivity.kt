package com.example.amotherone.ui.theme

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.amotherone.R
import com.example.amotherone.data.ImgurImage
import com.example.amotherone.data.ImgurViewModel

class FullPreviewActivity : AppCompatActivity() {

    private val viewModel: ImgurViewModel by viewModels()
    private lateinit var selectedImage: ImgurImage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_preview)

        val imageUrl = intent?.getStringExtra("image_url")
        val imageId = intent?.getStringExtra("image_id")

        if (imageUrl == null || imageId == null) {
            finish() // Missing data, safely exit
            return
        }

        val imageView: ImageView = findViewById(R.id.fullImageView)
        val deleteButton: Button = findViewById(R.id.btnDelete)
        val closeButton: Button = findViewById(R.id.btnClose)

        Glide.with(this).load(imageUrl).into(imageView)

        deleteButton.setOnClickListener {
            viewModel.delete(imageId) {
                finish()
            }
        }

        closeButton.setOnClickListener {
            finish()
        }
    }}
