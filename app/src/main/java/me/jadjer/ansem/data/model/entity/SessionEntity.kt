package me.jadjer.ansem.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sessions")
data class SessionEntity (
    @PrimaryKey val sessionId: Int,
    val name: String,
    val description: String,
    val dateStart: String,
    val dateEnd: String,
    val isActive: Boolean
)