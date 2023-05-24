package com.wantech.mytasker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.wantech.mytasker.presentation.TaskViewModel
import com.wantech.mytasker.ui.theme.MyTaskerTheme
import com.wantech.mytasker.util.NavigationHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val taskViewModel: TaskViewModel by viewModels()
        setContent {
            MyTaskerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val taskState = taskViewModel.state.collectAsState()
                    val navController = rememberNavController()
                    NavigationHost(navHostController = navController, taskState = taskState.value)
                }
            }
        }
    }
}

