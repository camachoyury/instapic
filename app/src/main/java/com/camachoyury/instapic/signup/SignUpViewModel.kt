package com.camachoyury.instapic.signup

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.camachoyury.instapic.User

import com.google.firebase.auth.*
import com.google.firebase.database.DatabaseReference
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception

@ActivityScoped
class SignUpViewModel @ViewModelInject constructor(val auth: FirebaseAuth, val dbReference: DatabaseReference) : ViewModel() {


    var emailNoExist: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    private lateinit var error: MutableLiveData<String>
    public var stateSignUp: MutableLiveData<Boolean> = MutableLiveData<Boolean>()


    public fun mailAlreadyExist(email: String) {
        auth.fetchSignInMethodsForEmail(email).addOnCompleteListener {
            if (it.isSuccessful) {
                val signInMethods = it.result!!.signInMethods ?: emptyList<String>()
        if (signInMethods.isEmpty()) {
                    emailNoExist.value = true
                }
            } else {
                emailNoExist.value = false
            }
        }
    }


    fun createUser(email: String, fullName: String, password: String) {

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    dbReference.createUser(User(it.result!!.user!!.uid, fullName, email),
                        onSuccess = { stateSignUp.value = true },
                        onError = {
                            error.value = it.toString()
                        })
                } else {
                    error.value = it.result.toString()
                }
            }
    }

    private fun DatabaseReference.createUser(
        user: User,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val reference = child("users").child(user.uid!!)
        reference.setValue(user).addOnCompleteListener {
            if (it.isSuccessful) {
                onSuccess()
            } else {
                onError( " Error User not created")
            }
        }
    }

}

