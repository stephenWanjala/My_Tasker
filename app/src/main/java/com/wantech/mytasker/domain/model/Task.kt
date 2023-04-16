package com.wantech.mytasker.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    val taskId:Int?=null,
    val taskTittle:String,
    val taskBody:String,
    val startTime:Long,
    val endTime:Long,
    val completed:Boolean =false
)
