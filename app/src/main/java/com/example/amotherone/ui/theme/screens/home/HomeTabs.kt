package com.example.amotherone.ui.theme.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.amotherone.ui.components.SketchfabWebView

@Composable
fun HomeTabs() {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("Media", "Sketchfab")

    Column {
        TabRow(selectedTabIndex = selectedTabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(title) }
                )
            }
        }

        when (selectedTabIndex) {
            0 -> MediaGalleryScreen()
            1 -> SketchfabWebView()
        }
    }
}


