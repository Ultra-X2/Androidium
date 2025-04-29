package com.example.amotherone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import androidx.compose.runtime.CompositionLocalProvider
import com.example.amotherone.navigation.ROUTE_LOGIN
import com.example.amotherone.ui.themes.screens.login.Login_Screen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "login") {
                composable("login") { Login_Screen(navController) }

                // Add more routes here as needed
            }
        }
    }
}
