package com.example.amotherone.api

import com.example.amotherone.data.ImgurImage

data class ImgurImageUploadResponse(
    val data: ImgurImage,
    val success: Boolean,
    val status: Int
)
