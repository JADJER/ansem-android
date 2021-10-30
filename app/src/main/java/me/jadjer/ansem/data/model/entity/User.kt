package me.jadjer.ansem.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User (
    @PrimaryKey val userId: Int,
    val username: String,
    val first_name: String,
    val second_name: String,
    val last_name: String,
    val country: String,
    val city: String,
    val address: String,
    val mobile_no: String,
    val is_admin: Boolean
)