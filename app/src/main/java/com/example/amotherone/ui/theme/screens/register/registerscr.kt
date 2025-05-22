package com.example.amotherone.ui.theme.screens.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.amotherone.data.AuthViewModel
import com.example.amotherone.data.AuthViewModelFactory

@Composable
fun Register_Screen(
    navController: NavHostController,
    authViewModel: AuthViewModel = viewModel(
        factory = AuthViewModelFactory(navController, LocalContext.current)
    )
) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var fname by remember { mutableStateOf(TextFieldValue("")) }
    var lname by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier

            .fillMaxSize()
            .background(Color(0xFFECEFF1))
    ) {
        Text(
            text = "Register Screen",
            color = Color.Black,
            fontSize = 35.sp,
            fontFamily = FontFamily.Default
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = fname,
            onValueChange = { fname = it },
            label = {
                Text(
                    "Enter First Name",
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontFamily = FontFamily.Default
                )
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = lname,
            onValueChange = { lname = it },
            label = {
                Text(
                    "Enter Last Name",
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontFamily = FontFamily.Default
                )
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = {
                Text(
                    "Enter Email",
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontFamily = FontFamily.Monospace
                )
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = {
                Text(
                    "Enter Password",
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontFamily = FontFamily.Monospace
                )
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                authViewModel.SignUp(
                    email = email.text.trim(),
                    pass = password.text.trim(),
                    fname = fname.text.trim(),
                    lname = lname.text.trim()
                )
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Click to Sign Up",
                color = Color.Black,
                fontSize = 20.sp,
                fontFamily = FontFamily.Default
            )
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}
