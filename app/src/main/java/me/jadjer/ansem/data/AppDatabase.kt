package me.jadjer.ansem.data

import androidx.room.Database
import androidx.room.RoomDatabase
import me.jadjer.ansem.data.model.entity.*
import me.jadjer.ansem.data.model.dao.*

@Database(
    entities = [Request::class, User::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun requestDao(): RequestDao
    abstract fun userDao(): UserDao
}
