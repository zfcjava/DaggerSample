package com.sgjk.daggersample.register

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sgjk.daggersample.MyApplication
import com.sgjk.daggersample.R
import com.sgjk.daggersample.register.fragment.EnterDetailsFragment
import com.sgjk.daggersample.register.fragment.TermsAndConditionsFragment

class RegisterActivity : AppCompatActivity(){

    lateinit var registerModel: RegisterModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerModel = RegisterModel(((application) as MyApplication).userManager)

        //添加一个fragment到Activity中
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_holder, EnterDetailsFragment())
            .commit()
    }


    fun onDetailsEntered(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_holder, TermsAndConditionsFragment())
            .commit()
    }

    fun onTermsAndConditionsAccepted(){
        registerModel.registerUser()
        //TODO 进入主页
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }


}