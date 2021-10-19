package me.jadjer.ansem.di

import android.content.Context
import androidx.room.Room
import me.jadjer.ansem.data.AppDatabase
import me.jadjer.ansem.data.model.dao.EcuDao
import me.jadjer.ansem.data.model.dao.MotorcycleDao
import me.jadjer.ansem.data.model.dao.UserDao
import org.koin.dsl.module

val databaseModule = module {
    single { provideRoomDatabase(get()) }
    single { getUserDao(get()) }
    single { getEcuDao(get()) }
    single { getMotorcycleDao(get()) }
}

fun provideRoomDatabase(context: Context): AppDatabase {
    return Room.databaseBuilder(context, AppDatabase::class.java, "motoecu.database")
//        .fallbackToDestructiveMigration()
//        .allowMainThreadQueries()
        .build()
}

fun getUserDao(appDatabase: AppDatabase) : UserDao {
    return appDatabase.userDao()
}

fun getEcuDao(appDatabase: AppDatabase) : EcuDao {
    return appDatabase.ecuDao()
}

fun getMotorcycleDao(appDatabase: AppDatabase): MotorcycleDao {
    return appDatabase.motorcycleDao()
}