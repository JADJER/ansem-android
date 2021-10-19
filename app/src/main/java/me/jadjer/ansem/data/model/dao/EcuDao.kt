package me.jadjer.ansem.data.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import me.jadjer.ansem.data.model.entity.Ecu

@Dao
interface EcuDao {

    @Query("SELECT * FROM ecues")
    fun findAll(): LiveData<List<Ecu>>

//    @Insert
//    fun insertEcu(ecu: EcuDao)

//    @Delete
//    fun deleteEcu(ecu: EcuDao)
}
