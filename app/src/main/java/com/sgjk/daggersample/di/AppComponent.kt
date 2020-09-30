package com.sgjk.daggersample.di

import android.content.Context
import com.sgjk.daggersample.main.MainActivity
import com.sgjk.daggersample.register.RegisterActivity
import com.sgjk.daggersample.storage.StorageModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

//这是一个全局的声明周期 组件（容器）
@Singleton
@Component(modules = [StorageModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context):AppComponent
    }

    //为register提供注册
    fun inject(activity: RegisterActivity)

    //为MainActivity提供注册
    fun inject(activity: MainActivity)
}