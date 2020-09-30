package com.sgjk.daggersample.register

import android.util.Log
import com.sgjk.daggersample.scope.ActivityScope
import com.sgjk.daggersample.user.UserManager
import javax.inject.Inject

// Scopes this ViewModel to components that use @ActivityScope
//限制 使用该Model的必须在使用ActivityScope的容器中
@ActivityScope
class RegisterModel @Inject constructor(val userManager: UserManager) {

    init {
        Log.e("zfccc", "RegisterModel init")
    }

    private var username: String? = null
    private var password: String? = null
    private var acceptedTCs: Boolean? = null

    fun updateUserData(username:String,password:String){
        this.username = username
        this.password = password
    }

    fun acceptTCs(){
        acceptedTCs = true
    }

    fun registerUser(){
        assert(username != null)
        assert(password != null)
        assert(acceptedTCs == true)

        userManager.registerUser(username!!, password!!)
    }
}