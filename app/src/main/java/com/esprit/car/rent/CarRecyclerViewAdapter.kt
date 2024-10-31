package com.esprit.car.rent

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.esprit.car.rent.models.Car

class CarRecyclerViewAdapter(
    private val onItemActionsListener: OnItemActionsListener
) : RecyclerView.Adapter<CarRecyclerViewAdapter.FilesViewHolder>() {

    lateinit var context: Context

    interface OnItemActionsListener {
        fun onCarFavorite(carConstructor:String?,carModel:String?)
    }

    var carList = emptyList<Car>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class FilesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val carConstructor: TextView = itemView.findViewById(R.id.constructor_tv)
        private val carModel: TextView = itemView.findViewById(R.id.model_tv)
        private val carPrice: TextView = itemView.findViewById(R.id.price_tv)
        private val carAvailability: TextView = itemView.findViewById(R.id.availability_tv)
        private val isFavorite: ImageButton = itemView.findViewById(R.id.favorite_btn)
        private val carImage: ImageView = itemView.findViewById(R.id.car_image)

        fun bind(item: Car, position: Int) {
            carConstructor.text = item.constructor
            carModel.text = item.model
            "${item.price} DT".also { carPrice.text = it }
            carAvailability.text = item.availability
            isFavorite.setOnClickListener { onItemActionsListener.onCarFavorite(item.constructor,item.model) }
            when(item.model){
                "Captur"-> carImage.setImageResource(R.drawable.captur)
                "Symbol"-> carImage.setImageResource(R.drawable.symbol)
                "Polo 8"-> carImage.setImageResource(R.drawable.polo8)
                "Polo Sedan"-> carImage.setImageResource(R.drawable.polosedan)
                "Ibiza"-> carImage.setImageResource(R.drawable.ibiza)
                "Leon"-> carImage.setImageResource(R.drawable.leon)
                "Rio"-> carImage.setImageResource(R.drawable.kia_rio)
                "Picanto"-> carImage.setImageResource(R.drawable.picanto)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilesViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.car_item, parent, false)
        return FilesViewHolder(view)
    }

    override fun getItemCount(): Int = carList.size

    override fun onBindViewHolder(holder: FilesViewHolder, position: Int) {
        val item = carList[position]
        holder.bind(item, position)
    }
}