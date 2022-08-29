package com.example.dogedex.auth

import android.content.Context
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dogedex.R
import com.example.dogedex.databinding.FragmentLoginBinding
import com.example.dogedex.isValidEmail


class LoginFragment : Fragment() {

    interface LoginFragmentActions{
        fun onRegisterButtonClick()
        fun onLoginFieldsValidates(email: String, password : String)
    }

    private lateinit var loginFragmentActions: LoginFragmentActions
    private lateinit var binding: FragmentLoginBinding
    //con este metodo puedes llamar los metodos de loginfragmentactions desde cualquier parte de la aplicacion
    //sirve para passar el contextto al fragment
    override fun onAttach(context: Context){
        super.onAttach(context)
        loginFragmentActions = try{
            context as LoginFragmentActions
        }catch (e:ClassCastException){
            throw  ClassCastException("$context must implement LoginFragmentActions")
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater)
        binding.loginRegisterButton.setOnClickListener{
            loginFragmentActions.onRegisterButtonClick()
        }
        binding.loginButton.setOnClickListener{
            validateFields()
        }
        return binding.root
    }

    private fun validateFields(){
        val email = binding.emailEdit.text.toString()

        binding.emailInput.error =""
        binding.passwordInput.error=""

        if(!isValidEmail(email)){
            binding.emailInput.error = getString(R.string.email_is_not_valid)
            return
        }

        val password = binding.passwordEdit.text.toString()
        if(password.isEmpty()){
            binding.passwordInput.error = getString(R.string.pass_must_not_be_empty)
            return
        }

        loginFragmentActions.onLoginFieldsValidates(email,password)

    }

}