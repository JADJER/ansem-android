package me.jadjer.ansem.data.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import me.jadjer.ansem.data.model.entity.Request

@Dao
interface RequestDao {

    @Query("SELECT * FROM requests")
    fun getAllRequests(): LiveData<List<Request>>

    @Query("SELECT * FROM requests WHERE requestId == :requestId")
    fun getRequest(requestId: Int): LiveData<Request?>

    @Insert
    fun insertRequest(request: Request)

    @Update
    fun updateRequest(request: Request)

    @Delete
    fun deleteRequest(request: Request)
}
