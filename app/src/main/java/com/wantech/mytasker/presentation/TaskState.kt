package com.wantech.mytasker.presentation

import com.wantech.mytasker.domain.model.Task
import com.wantech.mytasker.util.UiText

data class TaskState(
    val tittle: String = "",
    val startTime: String?=null,
    val endTime: String?=null,
    val taskBody: String = "",
    val createButtonEnabled: Boolean = false,
    val tasks: List<Task> = emptyList(),
    val error: UiText? = null,
    val isLoading: Boolean = false
)
