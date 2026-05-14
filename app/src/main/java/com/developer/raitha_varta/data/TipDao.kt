package com.developer.raitha_varta.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TipDao {
    @Query("SELECT * FROM tips_table ORDER BY isUrgent DESC")
    fun getAllTips(): Flow<List<TipEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTips(tips: List<TipEntity>)

    @Query("DELETE FROM tips_table")
    suspend fun clearAll()
}