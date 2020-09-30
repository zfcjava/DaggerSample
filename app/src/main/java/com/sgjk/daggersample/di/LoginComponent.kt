package com.sgjk.daggersample.di

import com.sgjk.daggersample.login.LoginActivity
import dagger.Subcomponent


@Subcomponent
interface LoginComponent{

    @Subcomponent.Factory
    interface factory{
        fun create():LoginComponent
    }

    fun inject(activity: LoginActivity)
}