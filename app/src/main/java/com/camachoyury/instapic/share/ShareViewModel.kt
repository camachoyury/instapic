package com.camachoyury.instapic.share

import android.net.Uri
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.camachoyury.instapic.Constants
import com.camachoyury.instapic.Constants.FEED_POST
import com.camachoyury.instapic.Constants.USER
import com.camachoyury.instapic.model.Post
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.StorageReference
import dagger.hilt.android.scopes.ActivityScoped


class ShareViewModel   constructor(
    private val databaseReference: DatabaseReference,
    private val auth: FirebaseAuth,
    private val databaseStorage: StorageReference

) : ViewModel() {


//    private var shareLiveData: MutableLiveData<Boolean> = MutableLiveData()
//
//    fun share(uri: Uri) {
//
//        val uid = auth.currentUser!!.uid
//        databaseStorage.child(USER).child(uid).child(Constants.IMAGES)
//            .child(uri.lastPathSegment!!).putFile(uri).addOnSuccessListener {
//                it.storage.downloadUrl.addOnSuccessListener {
//                    databaseReference.child("images").child(uid).push().setValue(uri.toString())
//                        .addOnSuccessListener {
//                            databaseReference.child(
//                                FEED_POST
//                            ).child(uid).push().setValue(mkFeedPost(uid, uri.toString()))
//                                .addOnSuccessListener {
//                                    shareLiveData.value = true
//                                }
//                        }
//                }
//            }
//    }
//
//    private fun mkFeedPost(uid: String, imageDownloadUrl: String): Post {
//        return Post(
//            uid = uid,
//            username =  "Yury Camacho",
//            image = imageDownloadUrl,
//            caption = binding.captionInput.text.toString(),
//            photo = mUser.photo
//        )
//    }
}