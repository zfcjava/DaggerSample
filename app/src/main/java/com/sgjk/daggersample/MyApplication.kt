package com.sgjk.daggersample

import android.app.Application
import com.sgjk.daggersample.storage.SharedPreferencesStorage
import com.sgjk.daggersample.user.UserManager

class MyApplication :Application(){

    //懒加载一个单例
    open val userManager by lazy {
        UserManager(SharedPreferencesStorage(this))
    }

}