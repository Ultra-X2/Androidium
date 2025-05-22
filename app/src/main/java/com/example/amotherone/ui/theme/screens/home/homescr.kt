package com.example.amotherone.ui.theme.screens.home

//package com.example.amotherone.ui.theme.screens.home
//import android.media.browse.MediaBrowser



//import androidx.media3.exoplayer.ExoPlayer
//import androidx.media3.session.MediaBrowser
import android.content.Intent
import android.net.Uri
import androidx.annotation.OptIn
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import androidx.navigation.NavHostController
import com.example.amotherone.R

@OptIn(UnstableApi::class)
@Composable
fun Home_Screen(navController: NavHostController, padding: PaddingValues) {
    val context = LocalContext.current

    val infiniteTransition = rememberInfiniteTransition()
    val gradientOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 2000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val animatedBrush = Brush.linearGradient(
        colors = listOf(Color(0xFF6D1775), Color(0xFF750F43), Color(0xFF00050C)),
        start = Offset(0f, gradientOffset),
        end = Offset(gradientOffset, 1000f)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(animatedBrush)
    ) {

        // 🔁 Top box with local looping video
        Box(
            modifier = Modifier
                .shadow(elevation = 5.dp, shape = RoundedCornerShape(25.dp))
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(25.dp))
                .padding(horizontal = 8.dp)
        ) {
            AndroidView(factory = { context ->
                val player = ExoPlayer.Builder(context).build().apply {
                    val mediaItem = MediaItem.fromUri(
                        Uri.parse("android.resource://${context.packageName}/${R.raw.sii}")
                    )
                    setMediaItem(mediaItem)
                    repeatMode = ExoPlayer.REPEAT_MODE_ALL
                    playWhenReady = true
                    prepare()
                }

                PlayerView(context).apply {
                    useController = false // hide play/pause UI
                    this.player = player
                    resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                    layoutParams = android.view.ViewGroup.LayoutParams(
                        android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                        android.view.ViewGroup.LayoutParams.MATCH_PARENT
                    )
                }
            }, modifier = Modifier.fillMaxSize())
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 🗨️ Middle message box
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(25.dp))
                .fillMaxWidth()
                .height(150.dp)
                .background(Color(0x92000000))
        ) {
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .padding(horizontal = 0.dp)
                    .background(Color(0x005E5E5E))
            ) {
                Text(
                    "If anyone is interested in developing their skills in 3D Modeling, a quick thought based on my experience that might be helpful.",
                    color = Color.White,
                    modifier = Modifier
                        .padding(18.dp)
                        .background(Color(0x005E5E5E)),
                    fontSize = 20.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 🌐 Social media icons
            Box(
                modifier = Modifier
                    .width(100.dp)
                    .fillMaxHeight()
                    .shadow(5.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .background(Color(0xFF4B4B4B))
                    .padding(horizontal = 8.dp),
                contentAlignment = Alignment.TopEnd
            ) {
                Column {
                    // Instagram
                    IconButton(onClick = {
                        val uri = Uri.parse("http://instagram.com/_u/ultra_extended")
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        intent.setPackage("com.instagram.android")
                        if (intent.resolveActivity(context.packageManager) != null) {
                            context.startActivity(intent)
                        } else {
                            context.startActivity(
                                Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://instagram.com/ultra_extended")
                                )
                            )
                        }
                    }) {
                        Icon(Icons.Default.Share, contentDescription = "Instagram", tint = Color(0xFF070707))
                    }

                    // LinkedIn
                    IconButton(onClick = {
                        val linkedInIntent =
                            Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/YOUR_USERNAME"))
                        context.startActivity(linkedInIntent)
                    }) {
                        Icon(Icons.Default.Person, contentDescription = "LinkedIn", tint = Color(0xFF000000))
                    }

                    // YouTube
                    IconButton(onClick = {
                        val intent =
                            Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/YOUR_CHANNEL_ID"))
                        context.startActivity(intent)
                    }) {
                        Icon(Icons.Default.PlayArrow, contentDescription = "YouTube", tint = Color.Red)
                    }
                }
            }
        }
    }
}
