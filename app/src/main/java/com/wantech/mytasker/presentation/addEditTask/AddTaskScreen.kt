@file:OptIn(ExperimentalMaterial3Api::class)

package com.wantech.mytasker.presentation.addEditTask

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.wantech.mytasker.presentation.addEditTask.components.AddTaskAppBar
import com.wantech.mytasker.presentation.addEditTask.components.CategoryChipsSection
import com.wantech.mytasker.presentation.addEditTask.components.TaskTime
import com.wantech.mytasker.presentation.addEditTask.components.TaskTittle
import com.wantech.mytasker.util.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(navController: NavHostController) {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                AddTaskAppBar(closePage = {
                    navController.navigate(Screen.HomeScreen.route) {
                        popUpTo(Screen.AddEditScreen.route) {
                            inclusive = true
                        }
                        navController.popBackStack()
                    }
                })
            }) {
            val paddingValues = it
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues = paddingValues),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                val tittle = remember {
                    mutableStateOf(TextFieldValue(text = ""))
                }
                TaskTittle(tittle = tittle)
                CategoryChipsSection()
                TaskTime()
            }
        }
    }
}