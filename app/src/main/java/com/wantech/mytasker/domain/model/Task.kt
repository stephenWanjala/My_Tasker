package com.wantech.mytasker.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    val taskId: Int? = null,
    val taskTittle: String,
    val taskBody: String,
    val startTime: String,
    val endTime: String,
    val completed: Boolean = false
)
