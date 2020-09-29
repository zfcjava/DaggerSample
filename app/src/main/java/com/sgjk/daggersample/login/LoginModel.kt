package com.sgjk.daggersample.login


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sgjk.daggersample.user.UserManager

class LoginModel(val userManager: UserManager){

    private var _loginState = MutableLiveData<LoginState>()

    val loginState:LiveData<LoginState>
        get() = _loginState

    fun login(username: String, password: String) {
        if(userManager.loginUser(username,password)){
            _loginState.value = LoginSuccess
        }else{
            _loginState.value = LoginError
        }
    }

    fun unregister(){
        userManager.unregister()
    }

    fun getUsername(): String {
        return userManager.userName
    }

}