package com.example.dogedex

import android.util.Patterns

public fun isValidEmail(email: String?) : Boolean{
    return email.isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
}