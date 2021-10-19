package me.jadjer.ansem.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "motorcycles")
data class Motorcycle (
    @PrimaryKey val motorcycleId: Int,
    val name: String?,
    val description: String?,
    val price: Int,
//    @Embedded val ecu: EcuEntity
)
