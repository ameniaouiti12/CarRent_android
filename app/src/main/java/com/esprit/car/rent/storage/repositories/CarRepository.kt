package com.esprit.car.rent.storage.repositories

import com.esprit.car.rent.models.Car
import com.esprit.car.rent.storage.dao.CarDao

class CarRepository(private val dao: CarDao) {

    fun getAllCars(): List<Car> {
        return dao.getAllCars()
    }

    fun addCar(car: Car):Long{
        return dao.addCar(car)
    }

    fun deleteCar(car: Car){
        return dao.deleteCar(car)
    }

    fun updateCar(car: Car){
        return dao.updateCar(car)
    }

    fun findCarByModel(carModel:String):Car?{
        return dao.findCarByModel(carModel)
    }
}