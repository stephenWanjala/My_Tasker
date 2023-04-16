package com.wantech.mytasker.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wantech.mytasker.presentation.HomeScreen

@Composable
fun NavigationHost(navHostController: NavHostController) {
    NavHost(navController =navHostController , startDestination =Screen.HomeScreen.route){
        composable(Screen.HomeScreen.route) {
            HomeScreen(navController=navHostController)
        }
    }
}