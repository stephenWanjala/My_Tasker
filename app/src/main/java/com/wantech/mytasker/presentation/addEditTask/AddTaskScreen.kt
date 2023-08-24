@file:OptIn(ExperimentalMaterial3Api::class)

package com.wantech.mytasker.presentation.addEditTask

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.wantech.mytasker.R
import com.wantech.mytasker.presentation.TaskUiEvent
import com.wantech.mytasker.presentation.TaskViewModel
import com.wantech.mytasker.presentation.addEditTask.components.AddTaskAppBar
import com.wantech.mytasker.presentation.addEditTask.components.CategoryChipsSection
import com.wantech.mytasker.presentation.addEditTask.components.CreateTaskButton
import com.wantech.mytasker.presentation.addEditTask.components.TaskBody
import com.wantech.mytasker.presentation.addEditTask.components.TaskTime
import com.wantech.mytasker.presentation.addEditTask.components.TaskTittle
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    closePage: () -> Unit,
    onCreateTask: () -> Unit,
    taskViewModel: TaskViewModel = hiltViewModel()
) {
    val state = taskViewModel.state.collectAsState()
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                AddTaskAppBar(closePage = closePage)
            }) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues = paddingValues)
                    .verticalScroll(state = rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {

                TaskTittle(tittle = state.value.tittle,
                    onTextChange = {
                        taskViewModel.onEvent(TaskUiEvent.EnterTittle(value = it))
                    })
                CategoryChipsSection()
                TaskTime(
                    startTime = state.value.startTime?.stringToLocalTime(),
                    endTime = state.value.endTime?.stringToLocalTime(),
                    onStartTimeChange = { startTime ->
                        taskViewModel.onEvent(
                            TaskUiEvent.SelectStartTime(
                                value = startTime
                            )
                        )

                    },
                    onEndTimeChange = { endTime ->
                        taskViewModel.onEvent(TaskUiEvent.SelectEndTime(value = endTime))
                    }
                )
                TaskBody(body = state.value.taskBody,
                    onTextChange = {
                        taskViewModel.onEvent(TaskUiEvent.EnterTaskBody(it))
                    })
                CreateTaskButton(
                    buttonText = stringResource(R.string.create_task),
                    enabled = state.value.createButtonEnabled,
                    onclick = {
                        taskViewModel.onEvent(TaskUiEvent.SaveTask)
                        onCreateTask.invoke()
                    }
                )
            }
        }
    }
}


fun LocalTime.toEpochMillis(): Long =
    LocalDate.now().atTime(this).toInstant(ZoneOffset.UTC).toEpochMilli()

fun Long.toLocalTime(): LocalTime =
    Instant.ofEpochMilli(this).atZone(ZoneId.systemDefault()).toLocalTime()


fun String.stringToLocalTime(formatter:DateTimeFormatter=DateTimeFormatter.ISO_LOCAL_TIME):LocalTime = (LocalTime.parse(this,formatter))
