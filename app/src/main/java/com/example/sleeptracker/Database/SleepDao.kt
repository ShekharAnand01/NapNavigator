package com.example.sleeptracker.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface SleepDao {

    @Upsert
    suspend fun upsert(night: SleepNight)

    @Query("Select * from sleep_night order by nightId desc")
    fun getAllNights(): LiveData<List<SleepNight>>

    @Query("Select * from sleep_night order by nightId desc limit 1")
    suspend fun getNight(): SleepNight?

    @Query("Delete from sleep_night")
    suspend fun clear()

    @Query("SELECT * from sleep_night WHERE nightId = :key")
    suspend fun get(key: Long): SleepNight?
}