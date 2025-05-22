package com.example.amotherone.data

import com.example.amotherone.api.ImgurApiService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

class ImgurRepository {
    private val api = Retrofit.Builder()
        .baseUrl("https://api.imgur.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ImgurApiService::class.java)

    // Remove fixed clientId header

    suspend fun uploadImage(accessToken: String, file: File): ImgurImage? {
        val request = file.asRequestBody("image/*".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("image", file.name, request)
        val authHeader = "Bearer $accessToken"
        val response = api.uploadImage(authHeader, body)
        return response.body()?.data
    }

    suspend fun getImages(accessToken: String): List<ImgurImage>? {
        val authHeader = "Bearer $accessToken"
        val response = api.getImages(authHeader)
        return response.body()?.data
    }

    suspend fun deleteImage(accessToken: String, id: String): Boolean {
        val authHeader = "Bearer $accessToken"
        val response = api.deleteImage(authHeader, id)
        return response.isSuccessful
    }
}
