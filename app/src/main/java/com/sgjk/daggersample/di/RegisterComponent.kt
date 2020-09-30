package com.sgjk.daggersample.di

import com.sgjk.daggersample.register.RegisterActivity
import com.sgjk.daggersample.register.fragment.EnterDetailsFragment
import com.sgjk.daggersample.register.fragment.TermsAndConditionsFragment
import dagger.Subcomponent

//子Component需要关联AppComponent
@Subcomponent
interface RegisterComponent {

    @Subcomponent.Factory
    interface factory{
        fun create():RegisterComponent
    }

    //为register提供注册
    fun inject(activity: RegisterActivity)

    fun inject(fragment: EnterDetailsFragment)

    fun inject(fragment: TermsAndConditionsFragment)

}