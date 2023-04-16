package com.wantech.mytasker.data.repositoryImpl

import com.wantech.mytasker.data.local.TaskDao
import com.wantech.mytasker.domain.model.Task
import com.wantech.mytasker.domain.repository.TaskRepository
import com.wantech.mytasker.util.Resource
import kotlinx.coroutines.flow.Flow

class TaskRepositoryImpl(private val dao: TaskDao) : TaskRepository {
    override suspend fun insertTask(task: Task) = dao.insertTask(task)

    override suspend fun getAllTasks(): Resource<Flow<List<Task>>> =
        Resource.Success(data = dao.getAllTasks())


    override suspend fun getTaskById(taskId: Int): Resource<Task?> =
        Resource.Success(data = dao.getTaskById(taskId))

    override suspend fun deleteTask(task: Task) {
        dao.deleteTask(task = task)
    }

    override suspend fun updateTask(task: Task) =
        dao.updateTask(task = task)


    override suspend fun completedTasks(): Resource<Flow<List<Task>>> =
        Resource.Success(data = dao.getCompletedTasks())
}