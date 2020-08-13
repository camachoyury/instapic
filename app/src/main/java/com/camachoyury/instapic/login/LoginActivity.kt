package com.camachoyury.instapic.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.camachoyury.instapic.home.HomeActivity
import com.camachoyury.instapic.databinding.ActivityLoginBinding
import com.camachoyury.instapic.signup.SignUpActivity
import dagger.hilt.android.AndroidEntryPoint
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener

@AndroidEntryPoint
class LoginActivity : AppCompatActivity(), KeyboardVisibilityEventListener {

    private lateinit var binding: ActivityLoginBinding

    private  val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.loginBtn.setOnClickListener {

            val email = binding.emailInput.text.toString()
            val password = binding.passwordInput.text.toString()

            if (validate(email, password)) {
                loginViewModel.loginWithMail(email, password)
            } else {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }

        loginViewModel.isLogin.observe(this, Observer {
            if (it) {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
        })

        binding.createAccountText.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java ))
        }
    }

    override fun onVisibilityChanged(isOpen: Boolean) {
        if (isOpen) {
            binding.createAccountText.visibility = View.GONE
        } else {
            binding.createAccountText.visibility = View.VISIBLE
        }
    }

    private fun validate(email: String, password: String) =
        email.isNotEmpty() && password.isNotEmpty()


}


