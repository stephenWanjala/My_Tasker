package com.wantech.mytasker.domain.repository

import com.wantech.mytasker.domain.model.Task
import com.wantech.mytasker.util.Resource
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun  insertTask(task: Task)
    suspend fun getAllTasks():Resource<Flow<List<Task>>>

    suspend fun  getTaskById(taskId:Int):Resource<Task?>

    suspend fun deleteTask(task: Task)
    suspend fun updateTask(task: Task)

    suspend fun completedTasks():Resource<Flow<List<Task>>>
}