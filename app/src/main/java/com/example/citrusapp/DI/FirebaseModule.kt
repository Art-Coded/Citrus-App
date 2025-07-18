package com.example.citrusapp.DI

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    //                                             Singleton use cases guide v1.0

    //             provideFirebaseAuth                                                      provideFirestore

    //Check who is currently logged in (auth.currentUser)                         Store first name, last name, etc.
    //        Sign in, sign out, create account                        Store custom app data per user (roles, points, preferences)
    //           Send email verification                                    Check if user's email is verified (mirrored info)
    //          Update password or email                                      Update profile fields (first name, last name)
    //                Delete user

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()
}