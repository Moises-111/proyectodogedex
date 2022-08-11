package com.example.dogedex.auth

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dogedex.R
import com.example.dogedex.databinding.FragmentSingOutBinding


class SingOutFragment : Fragment() {

    private lateinit var binding: FragmentSingOutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSingOutBinding.inflate(layoutInflater)
        setuoSingUpButton()
        return binding.root
    }

    private fun setuoSingUpButton() {
        binding.signUpButton.setOnClickListener{
            validateFields()
        }
    }

    private fun validateFields() {
        val email = binding.emailEdit.text.toString()

        binding.emailInput.error =""
        binding.passwordInput.error=""
        binding.confirmPasswordInput.error=""

        if(!isValidEmail(email)){
            binding.emailInput.error = getString(R.string.email_is_not_valid)
            return
        }

        val password = binding.passwordEdit.text.toString()
        if(password.isEmpty()){
            binding.passwordInput.error = getString(R.string.pass_must_not_be_empty)
            return
        }

        val passwordConfirmation = binding.confirmPasswordEdit.text.toString()
        if(passwordConfirmation.isEmpty()){
            binding.confirmPasswordInput.error =getString(R.string.pass_must_not_be_empty)
            return
        }

    }

    private fun isValidEmail(email: String?) : Boolean{
        return email.isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


}