package me.jadjer.ansem.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User (
    @PrimaryKey val userId: Int,
    val name: String?,
    val username: String?,
    val email: String?,
    val password: String?,
    val token: String?
)