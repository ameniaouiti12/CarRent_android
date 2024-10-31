package com.esprit.car.rent.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.esprit.car.rent.CarRecyclerViewAdapter
import com.esprit.car.rent.R
import com.esprit.car.rent.alertDialog
import com.esprit.car.rent.models.Car
import com.esprit.car.rent.storage.viewModels.CarViewModel


class RentFragment : Fragment(),CarRecyclerViewAdapter.OnItemActionsListener {
    private lateinit var carViewModel: CarViewModel

    private val carList : List<Car> = listOf(
        Car(0,"Kia","Picanto",110.0f,"Disponible",false),
        Car(0,"Renault","Symbol",90.0f,"Disponible",false),
        Car(0,"Renault","Captur",180.0f,"Disponible",false),
        Car(0,"Seat","Ibiza",130.0f,"Disponible",false),
        Car(0,"Seat","Leon",250.0f,"Disponible",false),
        Car(0,"Kia","Rio",100.0f,"Disponible",false),
        Car(0,"Volkswagen","Polo 8",150.0f,"Disponible",false),
        Car(0,"Volkswagen","Polo Sedan",170.0f,"Disponible",false),
    )
    private lateinit var recyclerView : RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        carViewModel = ViewModelProvider(requireActivity() as AppCompatActivity)[CarViewModel::class.java]
        val view =  inflater.inflate(R.layout.fragment_rent, container, false)
        recyclerView = view.findViewById(R.id.car_list)
        val adapter = CarRecyclerViewAdapter(this)
        adapter.carList = carList
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        return view

    }

    override fun onCarFavorite(carConstructor: String?, carModel: String?) {
        requireContext().alertDialog("Add to Favourite",
            "Are you sure you want to add this car to you favourites ?",
            "Yes","No",null,
            positiveBtnClickListener = {dialog,_->
                carViewModel.findCarByModel(carModel!!)
                carViewModel.carByModel.observe(viewLifecycleOwner){car->
                    if (car != null) {
                        Toast.makeText(requireContext(),"This car is already in favourites",Toast.LENGTH_SHORT).show()
                        carViewModel.updateCar(car)
                    }else {
                        val carFromList = carList.find { it.model == carModel }
                        if (carFromList != null) {
                            carViewModel.addCar(carFromList)
                        }
                    }
                }
                dialog.dismiss()
            }, negativeBtnClickListener = {dialog,_-> dialog.dismiss()}).show()
    }
}