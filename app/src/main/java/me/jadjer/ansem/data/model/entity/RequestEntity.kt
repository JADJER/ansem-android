package me.jadjer.ansem.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "requests")
data class RequestEntity (
    @PrimaryKey val requestId: Int,
    val school: String,
    val class_no: String,
    val score: Double,
    val index : Int,
)
