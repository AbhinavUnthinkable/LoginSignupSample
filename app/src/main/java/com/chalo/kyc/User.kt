package com.chalo.kyc

import com.google.firebase.auth.FirebaseUser

data class User(
    val name:String?,
    val email:String?,
    val photo:String?,
    val active:Boolean = false
)
