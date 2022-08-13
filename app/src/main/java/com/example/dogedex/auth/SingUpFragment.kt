package com.example.dogedex.auth

import android.content.Context
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dogedex.R
import com.example.dogedex.databinding.FragmentSingUpBinding


class SingUpFragment : Fragment() {

    interface SignUpFragmentActions{
        fun onSignUpFieldsValidates(email: String, password : String,passwordConfirmacion : String)
    }

    private lateinit var signUpFragmentActions: SignUpFragmentActions
    //con este metodo puedes llamar los metodos de loginfragmentactions desde cualquier parte de la aplicacion
    //sirve para passar el contextto al fragment
    override fun onAttach(context: Context){
        super.onAttach(context)
        signUpFragmentActions = try{
            context as SignUpFragmentActions
        }catch (e:ClassCastException){
            throw  ClassCastException("$context must implement SignUpFragmentActions")
        }
    }

    private lateinit var binding: FragmentSingUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSingUpBinding.inflate(layoutInflater)
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

        if(password != passwordConfirmation){
            binding.passwordInput.error =  getString(R.string.password_do_not_match)
        }

        signUpFragmentActions.onSignUpFieldsValidates(email,password,passwordConfirmation)

    }

    private fun isValidEmail(email: String?) : Boolean{
        return email.isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


}