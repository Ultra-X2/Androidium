package com.example.amotherone.ui.theme.screens.login


import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.amotherone.R
import com.example.amotherone.api.ImgurAuthConfig
import com.example.amotherone.data.AuthViewModel
import com.example.amotherone.data.AuthViewModelFactory

@Composable
fun LoginScreen(
    navController: NavHostController, padding: PaddingValues,
    authViewModel: AuthViewModel = viewModel(
        factory = AuthViewModelFactory(navController, LocalContext.current)
    )
) {

    val context = LocalContext.current

    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }

    // Animated Gradient Background
    val infiniteTransition = rememberInfiniteTransition()
    val gradientOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val animatedBrush = Brush.linearGradient(
        colors = listOf(Color(0xFF6D1775), Color(0xFF750F43), Color(0xFFFF003B)),
        start = Offset(0f, gradientOffset),
        end = Offset(gradientOffset, 10000f)
    )

    // UI
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(animatedBrush)
            .padding(24.dp)
            .padding(padding)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(30.dp))
        ) {
            Image(painter = painterResource(id= R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .shadow(elevation = 10.dp) )
            Spacer(Modifier.height(10.dp))
            Text(
                text = "Login",
                color = Color.White,
                fontSize = 32.sp,
                fontFamily = FontFamily.Monospace,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email", color = Color.White) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(15.dp)
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password", color = Color.White) },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(15.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    authViewModel.login(
                        email = email.text.trim(),
                        pass = password.text.trim()
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text("Log In", color = Color.White)
            }

            Text(
                text = "Don't have an account? Register",
                color = Color.White,
                modifier = Modifier
                    .clickable { navController.navigate("register") }
                    .padding(top = 16.dp),
                fontSize = 12.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { navController.navigate("home") },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text("Continue as Guest", color = Color.White)
            }
        }
    }
}


fun launchImgurLogin(context: Context) {
    val uri = Uri.parse("${ImgurAuthConfig.AUTH_URL}?client_id=${ImgurAuthConfig.CLIENT_ID}" +
            "&response_type=token&redirect_uri=${ImgurAuthConfig.REDIRECT_URI}")
    val customTabsIntent = CustomTabsIntent.Builder().build()
    customTabsIntent.launchUrl(context, uri)
}
/*
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController(),padding)
}*/

