package com.wantech.mytasker.presentation

import androidx.lifecycle.ViewModel
import com.wantech.mytasker.domain.repository.TaskRepository
import javax.inject.Inject

class TaskViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel()