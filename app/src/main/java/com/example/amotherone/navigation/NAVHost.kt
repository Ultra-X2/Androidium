package com.example.amotherone.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController= rememberNavController(),
    startDestination: String= ROUTE_HOME
) {
    NavHost(modifier = modifier, startDestination = startDestination, navController = navController){}
    
}