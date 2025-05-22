package com.example.amotherone.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.io.File

class ImgurViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ImgurRepository()
    private var accessToken: String? = null

    private val _images = MutableLiveData<List<ImgurImage>>(emptyList())
    val images: LiveData<List<ImgurImage>> = _images

    /**
     * Set the Imgur access token (called after successful OAuth login)
     */
    fun setAccessToken(token: String) {
        accessToken = token
        loadImages()
    }

    /**
     * Load images from the authenticated Imgur account
     */
    fun loadImages() {
        val token = accessToken ?: return
        viewModelScope.launch {
            try {
                _images.value = repository.getImages(token)
            } catch (e: Exception) {
                e.printStackTrace()
                // Handle error (e.g., notify UI)
            }
        }
    }

    /**
     * Upload an image and refresh the gallery
     */
    fun upload(file: File, onDone: () -> Unit) {
        val token = accessToken ?: return
        viewModelScope.launch {
            try {
                repository.uploadImage(token, file)
                loadImages()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                onDone()
            }
        }
    }

    /**
     * Delete an image by ID and refresh the gallery
     */
    fun delete(id: String, onDone: () -> Unit) {
        val token = accessToken ?: return
        viewModelScope.launch {
            try {
                repository.deleteImage(token, id)
                loadImages()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                onDone()
            }
        }
    }
}
