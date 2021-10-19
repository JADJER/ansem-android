package me.jadjer.ansem.data.repository

import me.jadjer.ansem.data.model.entity.Motorcycle

interface MotorcycleRepository {
    fun createMotorcycle(motorcycle: Motorcycle)
    fun getMotorcycles(): List<Motorcycle>
    fun getMotorcycle(motorcycleId: Int): Motorcycle
    fun updateMotorcycle(motorcycle: Motorcycle)
    fun deleteMotorcycle(motorcycle: Motorcycle)
}