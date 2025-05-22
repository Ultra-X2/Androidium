package com.example.amotherone.data

import java.io.Serializable

data class ImgurImage(
    val id: String,
    val link: String,
    val datetime: Long,
    val deletehash: String? = null
): Serializable
