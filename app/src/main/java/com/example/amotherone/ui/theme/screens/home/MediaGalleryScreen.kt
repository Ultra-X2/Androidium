package com.example.amotherone.ui.theme.screens.home

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toOffset
import coil.compose.rememberAsyncImagePainter
import com.example.amotherone.utils.RequestMediaPermission
import com.example.amotherone.utils.loadImagesFromStorage
import kotlin.math.abs

@Composable
fun MediaGalleryScreen() {
    val context = LocalContext.current
    var imageUris by remember { mutableStateOf<List<Uri>>(emptyList()) }

    RequestMediaPermission {
        imageUris = loadImagesFromStorage(context)
    }

    val gridState = rememberLazyGridState()

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 120.dp),
        state = gridState,
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(4.dp)
    ) {
        itemsIndexed(imageUris) { index, uri ->
            MediaImageCard(uri = uri, gridState = gridState, index = index)
        }
    }
}

@Composable
fun MediaImageCard(uri: Uri, gridState: LazyGridState, index: Int) {
    val visibleInfo = gridState.layoutInfo.visibleItemsInfo.firstOrNull { it.index == index }

    val blurAmount: Float
    val alpha: Float

    if (visibleInfo != null) {
        val viewportHeight = gridState.layoutInfo.viewportEndOffset
        val itemCenter = (visibleInfo.offset + visibleInfo.offset / 2F).toOffset()

        // Distance from center of screen
        val centerOffset = abs((viewportHeight / 2) - itemCenter.y)

        // Normalize to [0, 1]
        val normalized = (centerOffset / (viewportHeight / 2)).coerceIn(0f, 4f)

        blurAmount = normalized * 0f  // 0 to 15.dp
        alpha = 2f - normalized * 2f // 1 to 0.3
    } else {
        blurAmount = 15f
        alpha = 0.5f
    }

    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = rememberAsyncImagePainter(uri),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .aspectRatio(1f)
                .blur(blurAmount.dp)
                .graphicsLayer { this.alpha = alpha },
            contentScale = ContentScale.Crop
        )
    }
}

