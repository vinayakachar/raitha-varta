package com.developer.raitha_varta.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tips_table")
data class TipEntity(
    @PrimaryKey
    val id: String = "",
    val category: String = "",
    val titleEn: String = "",
    val titleKn: String = "",
    val descEn: String = "",
    val descKn: String = "",
    val imageUrl: String = "",
    val isUrgent: Boolean = false
)