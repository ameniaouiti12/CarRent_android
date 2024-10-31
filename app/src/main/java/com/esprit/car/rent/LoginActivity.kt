package com.esprit.car.rent

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.esprit.car.rent.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreferences : SharedPreferences
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: AppCompatButton
    private lateinit var rememberMe: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        sharedPreferences = getSharedPreferences(getString(R.string.preferences), MODE_PRIVATE)
        setContentView(binding.root)
        val username = sharedPreferences.getString("username",null)
        val password = sharedPreferences.getString("password",null)
        usernameEditText = binding.usernameEditText
        passwordEditText = binding.passwordEditText
        loginButton = binding.loginButton
        rememberMe = binding.rememberMe
        val intent = Intent(this,WelcomeActivity::class.java)
        val editor = sharedPreferences.edit()
        loginButton.setOnClickListener {
            if (username == null || password == null){
                editor.putString("username",usernameEditText.text.toString().trim())
                editor.putString("password",passwordEditText.text.toString().trim())
                editor.putBoolean("rememberMe",rememberMe.isChecked)
                intent.putExtra("username",usernameEditText.text.toString().trim())
                editor.apply()
                startActivity(intent)
            } else if (validateInputs(username,password)){
                editor.putBoolean("rememberMe",rememberMe.isChecked)
                editor.apply()
                intent.putExtra("username",username)
                startActivity(intent)
            }
        }

    }

    private fun validateInputs(sharedUsername : String?,sharedPassword:String?): Boolean {
        val username = usernameEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        if (username.isEmpty()) {
            usernameEditText.error = "Username must not be empty"
            return false
        }else if (username != sharedUsername) {
            usernameEditText.error = "Wrong username"
            return false
        }

        if (password.isEmpty()) {
            passwordEditText.error = "Password must not be empty"
            return false
        }else if (password != sharedPassword) {
            passwordEditText.error = "Wrong password"
            return false
        }

        return true
    }
}