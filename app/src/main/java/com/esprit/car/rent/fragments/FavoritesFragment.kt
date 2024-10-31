package com.esprit.car.rent.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.esprit.car.rent.FavouritesRecyclerViewAdapter
import com.esprit.car.rent.R
import com.esprit.car.rent.alertDialog
import com.esprit.car.rent.models.Car
import com.esprit.car.rent.storage.viewModels.CarViewModel

class FavoritesFragment : Fragment(),FavouritesRecyclerViewAdapter.OnItemActionsListener {
    private lateinit var carViewModel: CarViewModel
    private lateinit var recyclerView : RecyclerView
    private var carList = mutableListOf<Car>()
    private lateinit var adapter: FavouritesRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        carViewModel = ViewModelProvider(requireActivity() as AppCompatActivity)[CarViewModel::class.java]
        val view =  inflater.inflate(R.layout.fragment_rent, container, false)
        recyclerView = view.findViewById(R.id.car_list)
        adapter = FavouritesRecyclerViewAdapter(this)
        carViewModel.getAllCars()
        carViewModel.allCars.observe(viewLifecycleOwner){ list ->
            if (list.isNotEmpty()){
                carList = list.toMutableList()
                adapter.carList = carList
            }else {
                //Toast.makeText(requireContext(),"There is no favourites", Toast.LENGTH_SHORT).show()
            }
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        return view
    }

    override fun onDeleteCar(carConstructor: String?, carModel: String?, position: Int) {
        requireContext().alertDialog("Add to Favourite",
            "Are you sure you want to add this car to you favourites ?",
            "Yes","No",null,
            positiveBtnClickListener = {dialog,_->
                carViewModel.findCarByModel(carModel!!)
                carViewModel.carByModel.observe(viewLifecycleOwner){car->
                    if (car != null) {
                        carViewModel.deleteCar(car)
                        carList.removeIf{ it.model == carModel }
                        adapter.carList = carList
                    }else {
                        Toast.makeText(requireContext(),"Error",Toast.LENGTH_SHORT).show()
                    }
                }
                dialog.dismiss()
            }, negativeBtnClickListener = {dialog,_-> dialog.dismiss()}).show()
    }

    override fun onPause() {
        super.onPause()
        carList.clear()
    }

}