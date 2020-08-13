package com.camachoyury.instapic.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityRetainedComponent::class)
 object FirebaseModule{

    @Provides
    fun providesFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    fun providesDatabase() = FirebaseDatabase.getInstance().reference

    @Provides
    fun providesDataStorage() = FirebaseStorage.getInstance().reference
}