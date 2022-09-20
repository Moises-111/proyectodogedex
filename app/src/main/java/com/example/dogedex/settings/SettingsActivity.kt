package com.example.dogedex.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dogedex.R
import com.example.dogedex.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logoutButton.setOnClickListener{
            logout()
        }
    }

    private fun logout() {
        TODO("Not yet implemented")
    }
}