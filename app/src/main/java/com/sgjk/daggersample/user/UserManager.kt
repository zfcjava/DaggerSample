package com.sgjk.daggersample.user

import com.sgjk.daggersample.storage.Storage

//定义两个常量

private const val REGISTERED_USER = "registered_user"
private const val USER_SUFFIX = "user_suffix"

class UserManager(private val storage: Storage){

    var userDataRepository: UserDataRepository? = null

    val userName: String
        get() = storage.getString(REGISTERED_USER)


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
        userDataRepository = null
    }

    fun unregister(){
        storage.setString(REGISTERED_USER, "")
        storage.setString("$userName$USER_SUFFIX", "")
        logout()
    }

    private fun userJustLogin(){
        userDataRepository = UserDataRepository(this)
    }

    fun isUserLoggedIn(): Boolean {
        return userDataRepository != null
    }

}