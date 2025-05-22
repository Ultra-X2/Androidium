package com.example.amotherone.utils

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.MediaStore

fun loadImagesFromStorage(context: Context): List<Uri> {
    val imageUris = mutableListOf<Uri>()
    val collection = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
    val projection = arrayOf(MediaStore.Images.Media._ID)
    val sortOrder = "${MediaStore.Images.Media.DATE_ADDED} DESC"

    context.contentResolver.query(collection, projection, null, null, sortOrder)?.use { cursor ->
        val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
        while (cursor.moveToNext()) {
            val id = cursor.getLong(idColumn)
            val uri = ContentUris.withAppendedId(collection, id)
            imageUris.add(uri)
        }
    }
    return imageUris
}

