package com.sgjk.daggersample.di

import com.sgjk.daggersample.main.MainActivity
import com.sgjk.daggersample.scope.LoggedUserScope
import com.sgjk.daggersample.settings.SettingsActivity
import dagger.Subcomponent

/**
 * UserComponent 用来存储登录之后用户信息的状态。因此可以作为
 * 是否登录的标志。存储在UserManager中
 * 将原有UserRepository限制在UserComponent中，以便于MainActivity和SettingsActivity共享该实例。
 * 我们使用注解ActivityScope来管理Activity生命周期。当有多个Activity生命周期，我们需要一个新的注解---LoggedUserScope
 *
 * 我们使用LoggedUserScope在UserComponent和UserDataRepository上面，目的就是UserComponent能够提供唯一的UserDataRepository实例
 */
@LoggedUserScope
@Subcomponent
interface UserComponent {

    @Subcomponent.Factory
    interface Factory{
        fun create():UserComponent
    }

    //为MainActivity提供注册
    fun inject(activity: MainActivity)

    //为设置提供注册
    fun inject(activity: SettingsActivity)
}