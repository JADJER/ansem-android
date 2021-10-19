package me.jadjer.ansem.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ecues")
data class Ecu (
    @PrimaryKey val ecuId: Int,
    val token: String?,
    val mac: String?,
)
