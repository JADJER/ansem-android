package me.jadjer.ansem.data.model.entity

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithMotorcycles (
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "motorcycleId"
    )
    val motorcycles: List<Motorcycle>
)