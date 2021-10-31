package me.jadjer.ansem.app.di

import android.content.Context
import androidx.room.Room
import me.jadjer.ansem.data.AppDatabase
import me.jadjer.ansem.data.model.dao.RequestDao
import me.jadjer.ansem.data.model.dao.UserDao
import org.koin.dsl.module

val databaseModule = module {
    single { provideRoomDatabase(get()) }
    single { getUserDao(get()) }
    single { getRequestDao(get()) }
}

fun provideRoomDatabase(context: Context): AppDatabase {
    return Room.databaseBuilder(context, AppDatabase::class.java, "ansem.sqlite")
//        .fallbackToDestructiveMigration()
//        .allowMainThreadQueries()
        .build()
}

fun getUserDao(appDatabase: AppDatabase) : UserDao {
    return appDatabase.userDao()
}

fun getRequestDao(appDatabase: AppDatabase) : RequestDao {
    return appDatabase.requestDao()
}