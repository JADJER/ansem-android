package me.jadjer.ansem.data.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import me.jadjer.ansem.data.model.entity.Motorcycle

@Dao
interface MotorcycleDao {

    @Query("SELECT * FROM motorcycles")
    fun getAllMotorcycles(): LiveData<List<Motorcycle>>

    @Query("SELECT * FROM motorcycles WHERE motorcycleId == :motorcycleId")
    fun getMotorcycle(motorcycleId: Int): LiveData<Motorcycle>

//    @Query("SELECT * FROM motorcycles WHERE motorcycleId == :motorcycleId")
//    fun searchMotorcycles(query: String) : LiveData<List<MotorcycleEntity>>

    @Insert
    fun insertMotorcycle(motorcycle: Motorcycle)

    @Delete
    fun deleteMotorcycle(motorcycle: Motorcycle)
}
