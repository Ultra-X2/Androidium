package com.example.amotherone.api

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ImgurApiService {

    @Multipart
    @POST("3/image")
    suspend fun uploadImage(
        @Header("Authorization") authHeader: String,
        @Part image: MultipartBody.Part
    ): Response<ImgurImageUploadResponse>

    @GET("3/account/me/images")
    suspend fun getImages(
        @Header("Authorization") authHeader: String
    ): Response<ImgurImageListResponse>

    @DELETE("3/image/{deleteHash}")
    suspend fun deleteImage(
        @Header("Authorization") authHeader: String,
        @Path("deleteHash") imageId: String
    ): Response<ResponseBody>
}
