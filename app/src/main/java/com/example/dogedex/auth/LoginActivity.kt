package com.example.dogedex.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import com.example.dogedex.MainActivity
import com.example.dogedex.R
import com.example.dogedex.api.responses.ApiResponseStatus
import com.example.dogedex.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() ,LoginFragment.LoginFragmentActions,SingUpFragment.SignUpFragmentActions{

    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val loadingWheel = binding.loadingWheel
        viewModel.status.observe(this){
                status ->
            when(status){
                is ApiResponseStatus.Error ->{
                    loadingWheel.visibility = View.GONE
                    showErrorDialog(status.messageId)
                }
                is ApiResponseStatus.Loading -> loadingWheel.visibility = View.VISIBLE
                is ApiResponseStatus.Success -> loadingWheel.visibility = View.GONE
            }

        }
        viewModel.user.observe(this){
            user->
            if(user != null){
                startMainActivity()
            }
        }
    }

    private fun startMainActivity(){
        startActivity(Intent(this,MainActivity::class.java))
    }

    private  fun showErrorDialog(messageId: Int){
        AlertDialog.Builder(this)
            .setTitle(R.string.there_was_an_error)
            .setMessage(messageId)
            .setPositiveButton(android.R.string.ok){
                _,_->//va ocultar el dialog solamente
            }
            .create()
            .show()
    }

    override fun onRegisterButtonClick() {
       findNavController(R.id.nav_host_fragment)
       .navigate(LoginFragmentDirections.actionLoginFragmentToSingOutFragment())
    }

    override fun onLoginFieldsValidates(email: String, password: String) {
        viewModel.login(email,password)
    }

    override fun onSignUpFieldsValidates(
        email: String,
        password: String,
        passwordConfirmacion: String
    ) {
        viewModel.signUp(email,password,passwordConfirmacion)
    }
}