package com.esprit.car.rent

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.esprit.car.rent.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var sharedPreferences : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        sharedPreferences = getSharedPreferences(getString(R.string.preferences), MODE_PRIVATE)
        setContentView(binding.root)
        val username = sharedPreferences.getString("username",null)
        val password = sharedPreferences.getString("password",null)
        val rememberMe = sharedPreferences.getBoolean("rememberMe",false)
        if (username != null && password != null){
            if (rememberMe){
                Handler(Looper.getMainLooper()).postDelayed({
                    val intent = Intent(this,WelcomeActivity::class.java)
                    intent.putExtra("username",username)
                    startActivity(intent)
                    finish()
                },2000)
            }else {
                Handler(Looper.getMainLooper()).postDelayed({
                    val intent = Intent(this,LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                },2000)
            }
        }else {
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
                finish() },2000)
        }
    }
}