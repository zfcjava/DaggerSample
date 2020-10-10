package com.sgjk.daggersample.di

import android.content.Context
import com.sgjk.daggersample.login.LoginActivity
import com.sgjk.daggersample.main.MainActivity
import com.sgjk.daggersample.register.RegisterActivity
import com.sgjk.daggersample.register.fragment.EnterDetailsFragment
import com.sgjk.daggersample.register.fragment.TermsAndConditionsFragment
import com.sgjk.daggersample.settings.SettingsActivity
import com.sgjk.daggersample.storage.StorageModule
import com.sgjk.daggersample.user.UserManager
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

//这是一个全局的声明周期 组件（容器）
@Singleton
@Component(modules = [StorageModule::class,AppSubComponents::class])
interface AppComponent {

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context):AppComponent
    }

    //暴露 图中RegisterComponent.factory工厂方法
    //TODO 看看这个东西给谁用？ App吗
    fun registerComponent():RegisterComponent.factory

    fun loginComponent():LoginComponent.factory
    //错误使用
//    fun userComponent():UserComponent.Factory

    fun userManager(): UserManager

}