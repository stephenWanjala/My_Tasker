package com.wantech.mytasker.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wantech.mytasker.domain.model.Task

@Database(
    entities = [Task::class],
    version = 1,
    exportSchema = true
)
abstract class TaskDataBase : RoomDatabase() {
    abstract val dao: TaskDao
    companion object{
       const  val DATABASE_NAME ="task.db"
    }
}