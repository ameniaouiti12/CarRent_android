package com.esprit.car.rent.storage

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.esprit.car.rent.models.Car
import com.esprit.car.rent.storage.dao.CarDao

@Database(entities = [
    Car::class],
    version = 2, exportSchema = false)

abstract class AppDataBase : RoomDatabase() {
    abstract fun carDao() : CarDao

    companion object{
        @Volatile
        private var Instance : AppDataBase?=null

        fun getDatabase(context: Context) : AppDataBase {
            val tempInstance = Instance
            if (tempInstance != null){
                Log.d("database","is not null")
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "appDatabase"
                ).fallbackToDestructiveMigration().build()
                Instance = instance
                return instance
            }
        }
    }
}