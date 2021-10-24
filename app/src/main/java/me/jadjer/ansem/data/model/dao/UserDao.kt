package me.jadjer.ansem.data.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import me.jadjer.ansem.data.model.entity.User

@Dao
interface UserDao {

    @Query("SELECT * FROM users where userId = :userId")
    fun getUser(userId: Int): LiveData<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Update
    fun updateUser(user: User)
}
