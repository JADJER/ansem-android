package me.jadjer.ansem.data.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import me.jadjer.ansem.data.model.entity.User
import me.jadjer.ansem.data.model.entity.UserWithMotorcycles

@Dao
interface UserDao {

    @Transaction
    @Query("SELECT * FROM users where userId = :userId")
    fun getUserWithMotorcycles(userId: Int): LiveData<UserWithMotorcycles>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Update
    fun updateUser(user: User)
}
