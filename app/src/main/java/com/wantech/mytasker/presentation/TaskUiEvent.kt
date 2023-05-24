package com.wantech.mytasker.presentation

import com.wantech.mytasker.domain.model.Task

sealed class TaskUiEvent {
    data class EnterTittle(val value: String) : TaskUiEvent()
    data class EnterTaskBody(val value: String) : TaskUiEvent()
    data class SelectStartTime(val value: Long) : TaskUiEvent()
    data class SelectEndTime(val value: Long) : TaskUiEvent()
    object SaveTask : TaskUiEvent()
    data class DeleteTask(val task:Task) : TaskUiEvent()
    data class CompleteTask(val task:Task) : TaskUiEvent()
}