//package com.example.amotherone.ui.components
package com.example.amotherone.ui.theme.screens.home
import android.net.Uri
import android.widget.LinearLayout
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.amotherone.utils.RequestMediaPermission
import com.example.amotherone.utils.loadImagesFromStorage

@Composable
fun MasonryGallery(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val imageUris = remember { mutableStateListOf<Uri>() }

    RequestMediaPermission {
        imageUris.clear()
        imageUris.addAll(loadImagesFromStorage(context))
    }
    Spacer(modifier=Modifier.height(20.dp))
    AndroidView(

        factory = {

            RecyclerView(context).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
                )
                layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                adapter = PhotoAdapter(imageUris)
            }
        },
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun Side(){
    MasonryGallery()
}
