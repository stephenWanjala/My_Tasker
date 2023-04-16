package com.wantech.mytasker.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.wantech.mytasker.domain.model.Task
import com.wantech.mytasker.presentation.components.GreeterSection
import com.wantech.mytasker.presentation.components.HomeHeader
import com.wantech.mytasker.presentation.components.TaskItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(topBar = {
        HomeHeader()
    }) { paddingValues: PaddingValues ->

        LazyColumn(contentPadding = paddingValues) {
            item {
                GreeterSection()
            }
            items(10) {
                Spacer(modifier = Modifier.height(8.dp))
                TaskItem(
                    task = Task(
                        taskTittle = "Walk around",
                        taskBody = "Walk around Msu area",
                        startTime = System.currentTimeMillis()+(100*it),
                        endTime = System.currentTimeMillis() + 200000+(10*it),
                    ), onclickTask = {})
            }
        }

    }
}