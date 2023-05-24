package com.wantech.mytasker.data.local

import androidx.room.*
import com.wantech.mytasker.domain.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert
    suspend fun insertTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("SELECT * FROM Task")
    fun getAllTasks():Flow<List<Task>>

    @Query("SELECT * FROM Task where taskId=:taskId")
    suspend fun  getTaskById(taskId:Int):Task?

    @Update
    suspend fun updateTask(task: Task)

    @Query("SELECT * FROM Task WHERE completed=1")
    fun getCompletedTasks():Flow<List<Task>>


}