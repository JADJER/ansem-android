package me.jadjer.ansem.data.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import me.jadjer.ansem.data.model.entity.SessionEntity

@Dao
interface SessionDao {

    @Query("SELECT * FROM sessions where sessionId = :sessionId")
    fun getSession(sessionId: Int): LiveData<SessionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: SessionEntity)

    @Update
    fun updateUser(user: SessionEntity)
}
