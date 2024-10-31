package com.esprit.car.rent

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.esprit.car.rent.databinding.ActivityWelcomeBinding
import com.esprit.car.rent.fragments.FavoritesFragment
import com.esprit.car.rent.fragments.ProfileFragment
import com.esprit.car.rent.fragments.RentFragment

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding
    private lateinit var sharedPreferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(RentFragment())
        sharedPreferences = getSharedPreferences(getString(R.string.preferences), MODE_PRIVATE)
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.rent -> replaceFragment(RentFragment())
                R.id.profile -> replaceFragment(ProfileFragment())
                R.id.favorites -> replaceFragment(FavoritesFragment())
            }
            true
        }
        binding.logout.setOnClickListener {
            alertDialog("Logout","Are you sure you want to logout ?",
                "Yes","No",null,
                positiveBtnClickListener = {dialog,_->
                    val editor = sharedPreferences.edit()
                    editor.putBoolean("rememberMe",false)
                    editor.apply()
                    val intent = Intent(this@WelcomeActivity,LoginActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                    dialog.dismiss()
                    finish()
                },null, negativeBtnClickListener = {dialog,_-> dialog.dismiss()}).show()
        }

    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }
}