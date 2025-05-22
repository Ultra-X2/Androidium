package com.example.amotherone

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.amotherone.api.TokenStore
import com.example.amotherone.data.ImgurViewModel
import com.example.amotherone.navigation.BottomBar
import com.example.amotherone.ui.theme.screens.dash.DashboardScreen
import com.example.amotherone.ui.theme.screens.home.Home_Screen
import com.example.amotherone.ui.theme.screens.login.LoginScreen
import com.example.amotherone.ui.theme.screens.register.Register_Screen
import com.example.amotherone.ui.theme.screens.sketchfab.SketchfabScreen
import com.example.amotherone.ui.theme.screens.splash.SplashScreen
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: ImgurViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)

        setContent {
            val padding = PaddingValues()
            val navController = rememberNavController()
            viewModel = viewModel()

            // Inject stored token (if any)
            TokenStore.getAccessToken(this)?.let {
                viewModel.setAccessToken(it)
            }

            NavHost(navController = navController, startDestination = "Splash") {
                composable("Splash") { SplashScreen(navController) }
                composable("login") { LoginScreen(navController,padding) }
                composable("register") { Register_Screen(navController) }
                composable("home") { Home_Screen(navController,padding) }
                composable("dashboard") { DashboardScreen(viewModel, padding) }
                composable(NavRoutes.HOME) {
                    Scaffold(
                        bottomBar = { BottomBar(navController) }
                    ) { paddingValues ->
                        Home_Screen(navController, paddingValues)
                    }
                }

                composable(NavRoutes.DASHBOARD) {
                    Scaffold(
                        bottomBar = { BottomBar(navController) }
                    ) { paddingValues ->
                        DashboardScreen(viewModel, paddingValues)
                    }
                }

                composable(NavRoutes.SKETCHFAB) {
                    Scaffold(
                        bottomBar = { BottomBar(navController) }
                    ) { paddingValues ->
                        SketchfabScreen(paddingValues)
                    }
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        intent.data?.let { handleImgurRedirect(it) }
    }

    private fun handleImgurRedirect(uri: Uri) {
        val fragment = uri.fragment ?: return
        val params = fragment.split("&").associate {
            val (key, value) = it.split("=")
            key to value
        }

        val accessToken = params["access_token"]
        val refreshToken = params["refresh_token"]
        val expiresIn = params["expires_in"]

        if (accessToken != null) {
            TokenStore.saveTokens(
                context = this,
                accessToken = accessToken,
                refreshToken = refreshToken,
                expiresIn = expiresIn
            )
            viewModel.setAccessToken(accessToken)
            Toast.makeText(this, "Imgur Login Successful!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Imgur Login Failed", Toast.LENGTH_SHORT).show()
        }
    }
}
object NavRoutes {
    const val DASHBOARD = "dashboard"
    const val HOME = "home"
    const val SKETCHFAB = "sketchfab"
}
