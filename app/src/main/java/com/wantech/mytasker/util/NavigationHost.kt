package com.wantech.mytasker.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.wantech.mytasker.presentation.HomeScreen
import com.wantech.mytasker.presentation.addEditTask.AddTaskScreen

@Composable
fun NavigationHost(navHostController: NavHostController) {
    NavHost(navController =navHostController , startDestination =Screen.HomeScreen.route){
        composable(Screen.HomeScreen.route) {
            HomeScreen(navController=navHostController)
        }

        composable(Screen.AddEditScreen.route+   "?taskId={taskId}",
            arguments = listOf(
                navArgument(name = "taskId") {
                    type = NavType.IntType
                    defaultValue = -1
                },

            )){
            val taskId = it.arguments?.getInt(/* key = */ "taskId",/* defaultValue = */ -1)
            AddTaskScreen(navController = navHostController)
        }
    }
}