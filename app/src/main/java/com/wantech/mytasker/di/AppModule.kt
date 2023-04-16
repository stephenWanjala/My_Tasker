package com.wantech.mytasker.di

import android.app.Application
import androidx.room.Room
import com.wantech.mytasker.data.local.TaskDao
import com.wantech.mytasker.data.local.TaskDataBase
import com.wantech.mytasker.data.local.TaskDataBase.Companion.DATABASE_NAME
import com.wantech.mytasker.data.repositoryImpl.TaskRepositoryImpl
import com.wantech.mytasker.domain.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTaskDatabase(app: Application): TaskDataBase = Room.databaseBuilder(
        app,
        TaskDataBase::class.java,
        DATABASE_NAME
    )
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideTaskDao(taskDataBase: TaskDataBase): TaskDao = taskDataBase.dao

    @Singleton
    @Provides
    fun provideTaskRepository(dao: TaskDao): TaskRepository = TaskRepositoryImpl(dao = dao)

}