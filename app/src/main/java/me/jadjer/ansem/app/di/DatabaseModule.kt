package me.jadjer.ansem.app.di

import android.content.Context
import androidx.room.Room
import me.jadjer.ansem.data.AppDatabase
import me.jadjer.ansem.data.model.dao.RequestDao
import me.jadjer.ansem.data.model.dao.SessionDao
import org.koin.dsl.module

val databaseModule = module {
    single { provideRoomDatabase(get()) }
    single { getSessionDao(get()) }
    single { getRequestDao(get()) }
}

fun provideRoomDatabase(context: Context): AppDatabase {
    return Room.databaseBuilder(context, AppDatabase::class.java, "ansem.sqlite")
//        .fallbackToDestructiveMigration()
//        .allowMainThreadQueries()
        .build()
}

fun getSessionDao(appDatabase: AppDatabase) : SessionDao {
    return appDatabase.sessionDao()
}

fun getRequestDao(appDatabase: AppDatabase) : RequestDao {
    return appDatabase.requestDao()
}