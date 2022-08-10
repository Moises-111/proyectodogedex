package com.example.dogedex.auth

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dogedex.R
import com.example.dogedex.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    interface LoginFragmentActions{
        fun onRegisterButtonClick()
    }

    private lateinit var loginFragmentActions: LoginFragmentActions
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
        val binding = FragmentLoginBinding.inflate(layoutInflater)
        binding.loginRegisterButton.setOnClickListener{
            loginFragmentActions.onRegisterButtonClick()
        }
        return binding.root
    }
}