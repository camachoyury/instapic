package com.camachoyury.instapic.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.scopes.ActivityScoped

@ActivityScoped
class HomeViewModel @ViewModelInject constructor(private val auth: FirebaseAuth): ViewModel(){

     var isLogin: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    init{
        if (auth.currentUser == null){
            isLogin.value = false
        }
    }
}