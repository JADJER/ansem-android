package me.jadjer.ansem.data.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import me.jadjer.ansem.data.model.entity.RequestEntity

@Dao
interface RequestDao {

    @Query("SELECT * FROM requests")
    fun getAllRequests(): List<RequestEntity>

    @Query("SELECT * FROM requests WHERE requestId == :requestId")
    fun getRequest(requestId: Int): RequestEntity?

    @Insert
    fun insertRequest(request: RequestEntity)

    @Update
    fun updateRequest(request: RequestEntity)

    @Delete
    fun deleteRequest(request: RequestEntity)
}
