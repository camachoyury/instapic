package com.camachoyury.instapic.signup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.camachoyury.instapic.databinding.ActivitySignupBinding
import com.camachoyury.instapic.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {
    private val TAG = "RegisterActivity"

    private lateinit var binding: ActivitySignupBinding

    private var mEmail: String? = null
    private var fullName: String? = null
    private var password: String? = null
    private  val viewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.emailInput.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            private var timer: Timer = Timer()
            private val DELAY: Long = 5000
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                timer.cancel()
                timer = Timer()
                timer.schedule(
                    object : TimerTask() {
                        override fun run() {
                            viewModel.mailAlreadyExist(email = s.toString())

                        }
                    },
                    DELAY
                )
            }
        })

        viewModel.emailNoExist.observe(this, Observer {
            if (it){
                binding.fullNameInput.isEnabled = it
                binding.passwordInput.isEnabled = it

            }else{
                showToast("The User is already registered")
                finish()
            }

        })
        binding.registerBtn.setOnClickListener {
             var email: String = binding.emailInput.text.toString()
             var fullName: String = binding.fullNameInput.text.toString()
             var password: String = binding.passwordInput.text.toString()

            if (fullName.isNotEmpty () && password.isNotEmpty()) {
                viewModel.createUser(email = email ,fullName = fullName, password = password)
            } else {
                showToast("Please fill the fields ")

            }

        }

        viewModel.stateSignUp.observe(this, Observer {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        })
    }

    private fun Context.showToast(text: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, text, duration).show()
    }
}