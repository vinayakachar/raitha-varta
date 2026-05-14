package com.developer.raitha_varta.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SuccessStoryDao {

    @Query("SELECT * FROM success_stories")
    fun getAllStories(): Flow<List<SuccessStoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStories(stories: List<SuccessStoryEntity>)

    @Query("DELETE FROM success_stories")
    suspend fun clearAll()
}