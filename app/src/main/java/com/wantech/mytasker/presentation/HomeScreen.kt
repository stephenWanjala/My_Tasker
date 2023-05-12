package com.wantech.mytasker.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.wantech.mytasker.domain.model.Task
import com.wantech.mytasker.presentation.components.GreeterSection
import com.wantech.mytasker.presentation.components.HomeHeader
import com.wantech.mytasker.presentation.components.TaskItem
import com.wantech.mytasker.util.Screen
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    val lazyListState = rememberLazyListState()
    val showBar = remember {
        derivedStateOf { lazyListState.firstVisibleItemIndex==0 }
    }


    Scaffold(topBar = {
        AnimatedVisibility(visible = showBar.value) {
            HomeHeader()
        }
    },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.AddEditScreen.route)
                },
                elevation = FloatingActionButtonDefaults.elevation(
                    defaultElevation = 4.dp
                )
            ) {
                Icon(imageVector = Icons.Outlined.Add, contentDescription = "Add")
            }
        }) { paddingValues: PaddingValues ->

        LazyColumn(
            contentPadding = paddingValues,
            state = lazyListState
        ) {
            item {
                GreeterSection()
            }
            items(10) { index ->
                Spacer(modifier = Modifier.height(8.dp))
                TaskItem(
                    task = Task(
                        taskTittle = "Walk around",
                        taskBody = "Walk around Msu area",
                        startTime = System.currentTimeMillis() + (1000 * index),
                        endTime = System.currentTimeMillis() + 200000 + (100 * index),
                        completed = true
                    ),
                    onclickTask = { task ->
                        navController.navigate(Screen.AddEditScreen.route + "?taskId=${task.taskId}")
                    },
                )
            }
        }

    }
}


@Composable
fun Dp.dpToPx() = with(LocalDensity.current) { this@dpToPx.toPx() }