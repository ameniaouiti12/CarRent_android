package com.esprit.car.rent.storage.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.esprit.car.rent.models.Car
import com.esprit.car.rent.storage.AppDataBase
import com.esprit.car.rent.storage.repositories.CarRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CarViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: CarRepository

    init {
        val carDao = AppDataBase.getDatabase(application).carDao()
        repository = CarRepository(carDao)
    }

    private val _allCars = MutableLiveData<List<Car>>()
    val allCars: LiveData<List<Car>> = _allCars

    private val _carByModel = MutableLiveData<Car?>()
    val carByModel: LiveData<Car?> = _carByModel

    fun getAllCars() {
        viewModelScope.launch(Dispatchers.IO) {
            val cars = repository.getAllCars()
            _allCars.postValue(cars)
        }
    }

    fun addCar(car: Car): LiveData<Long> {
        val result = MutableLiveData<Long>()
        viewModelScope.launch(Dispatchers.IO) {
            val carId = repository.addCar(car)
            result.postValue(carId)
        }
        return result
    }

    fun deleteCar(car: Car): LiveData<Boolean> {
        val result = MutableLiveData<Boolean>()
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCar(car)
            result.postValue(true)
        }
        return result
    }

    fun updateCar(car: Car): LiveData<Boolean> {
        val result = MutableLiveData<Boolean>()
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateCar(car)
            result.postValue(true)
        }
        return result
    }

    fun findCarByModel(carModel: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val car = repository.findCarByModel(carModel)
            _carByModel.postValue(car)
        }
    }
}