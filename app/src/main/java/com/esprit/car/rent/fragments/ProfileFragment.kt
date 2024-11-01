package com.esprit.car.rent.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.esprit.car.rent.R


class ProfileFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val username = requireActivity().intent.getStringExtra("username")
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val profileName = view.findViewById<TextView>(R.id.username)
        profileName.text = username
        return view
    }

}