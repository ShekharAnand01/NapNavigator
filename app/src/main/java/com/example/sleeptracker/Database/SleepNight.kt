package com.example.sleeptracker.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sleep_night")
data class SleepNight(
    @PrimaryKey(autoGenerate = true) val nightId: Long = 0L,
    @ColumnInfo(name = "StartTime") val startTime: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "EndTime") var endTime: Long = startTime,
    @ColumnInfo(name = "Rating") var rating: Int = -1


)
