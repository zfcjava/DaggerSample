package com.sgjk.daggersample.user

import android.util.Log
import com.sgjk.daggersample.di.UserComponent
import com.sgjk.daggersample.storage.Storage
import javax.inject.Inject
import javax.inject.Singleton

//定义两个常量

private const val REGISTERED_USER = "registered_user"
private const val USER_SUFFIX = "user_suffix"

//因为UserManager只使用了一个
@Singleton
class UserManager @Inject constructor(private val storage: Storage, private val userComponentFactory: UserComponent.Factory){

    var userComponent: UserComponent? = null
        private set

    val userName: String
        get() = storage.getString(REGISTERED_USER)


    init {
        Log.e("zfc", "UserManager init111")
    }

    fun isUserRegistered() = storage.getString(REGISTERED_USER).isNotEmpty()

    fun registerUser(userName: String, password: String) {
        storage.setString(REGISTERED_USER, userName)
        storage.setString("$userName$USER_SUFFIX", password)
        userJustLogin()
    }

    fun loginUser(userName: String, password: String):Boolean {
        val registerName = this.userName
        if(registerName != userName) return false
        if(password != storage.getString("$userName$USER_SUFFIX"))
            return false
        userJustLogin()
        return true
    }


    fun logout() {
        userComponent = null
    }

    fun unregister(){
        storage.setString(REGISTERED_USER, "")
        storage.setString("$userName$USER_SUFFIX", "")
        logout()
    }

    private fun userJustLogin(){
        userComponent = userComponentFactory.create()
    }

    fun isUserLoggedIn(): Boolean {
        return userComponent != null
    }

}