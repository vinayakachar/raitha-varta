package com.developer.raitha_varta.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "success_stories")
data class SuccessStoryEntity(
    @PrimaryKey val id: String = "",
    val farmerNameKn: String = "",
    val farmerNameEn: String = "",
    val locationKn: String = "",
    val locationEn: String = "",
    val storyKn: String = "",
    val storyEn: String = "",
    val yieldIncrease: String = "",
    val imageUrl: String = ""
)