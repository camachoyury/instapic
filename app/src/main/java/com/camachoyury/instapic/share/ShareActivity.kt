package com.camachoyury.instapic.share

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.camachoyury.instapic.Constants.IMAGES
import com.camachoyury.instapic.Constants.USER
import com.camachoyury.instapic.FirebaseHelper
import com.camachoyury.instapic.ValueEventListenerAdapter
import com.camachoyury.instapic.databinding.ActivityShareBinding
import com.camachoyury.instapic.home.GlideApp
import com.camachoyury.instapic.home.HomeActivity
import com.camachoyury.instapic.model.Post
import com.camachoyury.instapic.model.User
import com.camachoyury.instapic.post.CameraPreviewActivity
import com.google.firebase.auth.FirebaseAuth


class ShareActivity: AppCompatActivity(){
    companion object{
         const val CAMERA_RESULT = "CAMERA_RESULT"
    }
    private lateinit var mFirebase: FirebaseHelper
    private lateinit var binding: ActivityShareBinding
    private lateinit var mUser: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val auth = FirebaseAuth.getInstance()
        binding = ActivityShareBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mFirebase = FirebaseHelper(this)
        mFirebase.currentUserReference().addValueEventListener(ValueEventListenerAdapter {
            mUser = it.getValue(User::class.java)!!
        })


        binding.shareText.setOnClickListener {
            share()
        }
    }

    override fun onResume() {
        super.onResume()
        if (HomeActivity.currentPic == null){
            startActivity(Intent(this, CameraPreviewActivity::class.java))
        }else{

            GlideApp.with(this).load(HomeActivity.currentPic).centerCrop().into(binding.postImage)
        }
    }


    private fun share() {
        binding.shareText.isEnabled = false
        val imageUri = HomeActivity.currentPic
        if (imageUri != null) {
            val uid = mFirebase.currentUid()

            mFirebase.storage.child(USER).child(mFirebase.currentUid()!!).child(IMAGES)
                .child(imageUri.lastPathSegment!!).putFile(imageUri).addOnSuccessListener {
                        it.storage.downloadUrl.addOnSuccessListener {uri->
                            mFirebase.database.child("images").child(uid!!).push()
                            .setValue(uri.toString())
                            .addOnSuccessListener {
                                mFirebase.database.child("feed-posts").child(uid)
                                    .push().setValue(mkFeedPost(uid!!, uri.toString()))
                                    .addOnSuccessListener {
                                        binding.shareText.isEnabled = true

//                                            startActivity(Intent(this,
//                                                ProfileActivity::class.java))
                                        finish()
                                    }
                            } }



                        }
            // add image to user images <- db
        }

    }
        private fun mkFeedPost(uid: String, imageDownloadUrl: String): Post {
            return Post(
                uid = uid,
                username = mUser.username?:"Yury Camacho",
                image = imageDownloadUrl,
                caption = binding.captionInput.text.toString(),
                photo = mUser.photo
            )
        }

//     fun DataSnapshot.asUser(): User? =
//        getValue(User::class.java)?.copy(uid = key)
}