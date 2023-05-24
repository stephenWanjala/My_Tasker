package com.wantech.mytasker.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wantech.mytasker.domain.model.Task
import com.wantech.mytasker.domain.repository.TaskRepository
import com.wantech.mytasker.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {

    private val _state = MutableStateFlow(TaskState())
    val state = _state.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = TaskState()
    )

    init {
        loadInitialTasks()
    }


    fun onEvent(event: TaskUiEvent) {
        when (event) {
            is TaskUiEvent.EnterTaskBody -> {
                _state.update { it.copy(taskBody = event.value) }
                enableCreateTaskButton()
            }

            is TaskUiEvent.EnterTittle -> {
                _state.update { it.copy(tittle = event.value) }
                enableCreateTaskButton()
            }

            TaskUiEvent.SaveTask -> {
                saveTask()
            }

            is TaskUiEvent.SelectStartTime -> {
                _state.update { it.copy(startTime = event.value) }
                enableCreateTaskButton()
            }

            is TaskUiEvent.SelectEndTime -> {
                _state.update { it.copy(endTime = event.value) }
                enableCreateTaskButton()
            }

            is TaskUiEvent.CompleteTask -> {
                viewModelScope.launch {
                    repository.updateTask(task = event.task.copy(completed = true))
                }
            }

            is TaskUiEvent.DeleteTask -> {
                viewModelScope.launch {
                    repository.deleteTask(task = event.task)
                }
            }
        }
    }


    private fun loadInitialTasks() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            when (val flowResource = repository.getAllTasks()) {
                is Resource.Error -> _state.update { it.copy(error = flowResource.uiText) }
                is Resource.Loading -> _state.update { it.copy(isLoading = true) }
                is Resource.Success -> {
                    flowResource.data?.let { tasksFlow: Flow<List<Task>> ->
                        tasksFlow.collectLatest { tasksList ->
                            _state.update { it.copy(tasks = tasksList) }
                        }
                    }
                }
            }
        }
        _state.update { it.copy(isLoading = false) }
    }


    private fun enableCreateTaskButton() {
        _state.update {
            it.copy(
                createButtonEnabled = (state.value.tittle.isNotEmpty() && state.value.taskBody.isNotEmpty() && state.value.startTime != null && state.value.endTime != null)
            )
        }
    }

    private fun saveTask() {
        viewModelScope.launch {
            state.value.apply {
                val task = Task(
                    taskBody = taskBody,
                    taskTittle = tittle,
                    startTime = startTime!!,
                    endTime = endTime!!
                )
                repository.insertTask(task = task)

            }
        }
    }

}