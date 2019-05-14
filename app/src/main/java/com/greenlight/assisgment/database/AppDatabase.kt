package com.greenlight.assisgment.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.greenlight.assisgment.entity.UserModel

@Database(entities = [UserModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userModelDao(): UserModelDao

    companion object {
        var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "myDB")
                        .allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }
}