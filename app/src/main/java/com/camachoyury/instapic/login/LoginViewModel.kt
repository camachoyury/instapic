package com.camachoyury.instapic.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.scopes.ActivityScoped

@ActivityScoped
class LoginViewModel @ViewModelInject constructor(private val auth: FirebaseAuth) : ViewModel() {

     var isLogin: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    public fun loginWithMail(email: String, password: String) {

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            isLogin.value = it.isSuccessful
        }
    }

}