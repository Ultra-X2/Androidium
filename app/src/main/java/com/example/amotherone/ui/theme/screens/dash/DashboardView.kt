@file:Suppress("DEPRECATION")

package com.example.amotherone.ui.theme.screens.dash

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.amotherone.data.ImgurImage
import com.example.amotherone.data.ImgurViewModel
import com.example.amotherone.ui.theme.FullPreviewActivity
import java.io.File

@Composable
fun DashboardScreen(viewModel: ImgurViewModel = viewModel(), padding: PaddingValues) {
    val context = LocalContext.current
    val images by viewModel.images.observeAsState(emptyList())
    val selectedUris = remember { mutableStateListOf<Uri>() }

    val imagePickerLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            selectedUris.add(it) // 1. Show immediately
            val file = uriToFile(context, it)
            file?.let { safeFile ->
                viewModel.upload(safeFile) {
                    selectedUris.remove(uri) // 2. Remove after upload
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            onClick = { imagePickerLauncher.launch("image/*") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Text("Upload to Imgur")
        }

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            // Show selected images (local Uri previews)
            items(selectedUris) { uri ->
                LocalImageCard(uri = uri)
            }

            // Show uploaded images from Imgur
            items(images) { image ->
                ImgurImageCard(image = image, onClick = {
                    val intent = Intent(context, FullPreviewActivity::class.java).apply {
                        putExtra("image_url", image.link)
                        putExtra("image_id", image.id)
                    }
                    context.startActivity(intent)
                    (context as? Activity)?.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                })
            }
        }
    }
}
@Composable
fun LocalImageCard(uri: Uri) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(Color.Black)
            .padding(8.dp)
            .shadow(elevation = 10.dp)
    ) {
        AsyncImage(

            model = uri,
            contentDescription = "Selected Image",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(15.dp))
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Uploading...",
            color = Color.White,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun ImgurImageCard(image: ImgurImage, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(Color.DarkGray)
            .clickable { onClick() }
            .padding(2.dp)
            .shadow(elevation = 10.dp)

    ) {
        AsyncImage(
            model = image.link,
            contentDescription = "Imgur Image",
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(15.dp))
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Uploaded: ${image.datetime}",
            color = Color.White,
            style = MaterialTheme.typography.bodySmall
        )
    }
}
fun uriToFile(context: Context, uri: Uri): File? {
    val inputStream = context.contentResolver.openInputStream(uri) ?: return null
    val tempFile = File.createTempFile("upload", ".jpg", context.cacheDir)
    tempFile.outputStream().use { outputStream ->
        inputStream.copyTo(outputStream)
    }
    return tempFile
}
