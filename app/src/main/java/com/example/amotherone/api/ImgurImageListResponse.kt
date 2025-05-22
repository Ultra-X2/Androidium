package com.example.amotherone.api

import com.example.amotherone.data.ImgurImage


data class ImgurImageListResponse(
    val data: List<ImgurImage>,
    val success: Boolean,
    val status: Int
)
