package com.developer.raitha_varta.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// 1. Add SuccessStoryEntity to the list
// 2. Increment version to 2
@Database(entities = [TipEntity::class, SuccessStoryEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun tipDao(): TipDao
    // 3. Add the SuccessStoryDao
    abstract fun successStoryDao(): SuccessStoryDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "raitha_varta_db"
                )
                    // 4. Add this to prevent crashes during the version upgrade
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}