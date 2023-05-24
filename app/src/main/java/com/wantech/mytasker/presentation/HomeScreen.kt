package com.wantech.mytasker.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.wantech.mytasker.R
import com.wantech.mytasker.domain.model.Task
import com.wantech.mytasker.presentation.components.GreeterSection
import com.wantech.mytasker.presentation.components.HomeHeader
import com.wantech.mytasker.presentation.components.TaskItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    taskState: TaskState,
    onFabCLick: () -> Unit,
    onClickTask: (Task) -> Unit
) {
    val lazyListState = rememberLazyListState()
    val showBar = remember {
        derivedStateOf { lazyListState.firstVisibleItemIndex == 0 }
    }



    Scaffold(topBar = {
        AnimatedVisibility(visible = showBar.value) {
            HomeHeader()
        }
    },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onFabCLick,
                elevation = FloatingActionButtonDefaults.elevation(
                    defaultElevation = 4.dp
                )
            ) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = stringResource(R.string.add)
                )
            }
        }) { paddingValues: PaddingValues ->

        LazyColumn(
            contentPadding = paddingValues,
            state = lazyListState
        ) {
            item {
                GreeterSection(tasksAvailable = taskState.tasks.size)
            }
            items(taskState.tasks) { task ->
                Spacer(modifier = Modifier.height(8.dp))
                TaskItem(
                    task = task,
                    onclickTask = { clickedTask ->
                        onClickTask(clickedTask)
                    },
                )
            }
        }

    }
}


@Composable
fun Dp.dpToPx() = with(LocalDensity.current) { this@dpToPx.toPx() }