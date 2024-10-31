package com.esprit.car.rent.storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.esprit.car.rent.models.Car
import kotlinx.coroutines.flow.Flow

@Dao
interface CarDao {
    @Query("SELECT * FROM car")
    fun getAllCars(): List<Car>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = Car::class)
    fun addCar(car: Car):Long

    @Update
    fun updateCar(car: Car)

    @Delete
    fun deleteCar(car: Car)

    @Query("SELECT * FROM car WHERE model = :carModel LIMIT 1")
    fun findCarByModel(carModel: String): Car?
}