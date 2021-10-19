package me.jadjer.ansem.data.model.entity

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithEcues (
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "ecuId"
    )
    val ecues: List<Ecu>
)