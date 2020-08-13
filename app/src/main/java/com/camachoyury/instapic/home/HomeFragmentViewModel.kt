package com.camachoyury.instapic.home

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.camachoyury.instapic.ValueEventListenerAdapter
import com.camachoyury.instapic.model.Post
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import dagger.hilt.android.scopes.FragmentScoped

@FragmentScoped
class HomeFragmentViewModel @ViewModelInject constructor(
    private val databaseReference: DatabaseReference,
    private val auth: FirebaseAuth
) : ViewModel() {

    public val postLiveData = MutableLiveData<List<Post>>()

    private lateinit var adapter: FeedAdapter

    fun loadPost() {

        val currentUser = auth.currentUser
        currentUser?.let {
            databaseReference.child("feed-posts").child(currentUser!!.uid)
                .addValueEventListener(ValueEventListenerAdapter {
                    postLiveData.value =  it.children.map { it.asFeedPost()!!}
                })
        }

    }
}

fun DataSnapshot.asFeedPost(): Post? =
    getValue(Post::class.java)?.copy(id = key!!)




