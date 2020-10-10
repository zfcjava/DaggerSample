package com.sgjk.daggersample

import android.app.Application
import com.sgjk.daggersample.di.AppComponent
import com.sgjk.daggersample.di.DaggerAppComponent
import com.sgjk.daggersample.storage.SharedPreferencesStorage
import com.sgjk.daggersample.user.UserManager

class MyApplication :Application(){

    val appComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }
}