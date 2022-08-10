package com.example.dogedex.auth

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.dogedex.R
import com.example.dogedex.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() ,LoginFragment.LoginFragmentActions{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onRegisterButtonClick() {
       findNavController(R.id.nav_host_fragment)
       .navigate(LoginFragmentDirections.actionLoginFragmentToSingOutFragment())
    }
}