package com.sgjk.daggersample.register

import android.app.Application
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sgjk.daggersample.MyApplication
import com.sgjk.daggersample.R
import com.sgjk.daggersample.main.MainActivity
import com.sgjk.daggersample.register.fragment.EnterDetailsFragment
import com.sgjk.daggersample.register.fragment.TermsAndConditionsFragment
import javax.inject.Inject

class RegisterActivity : AppCompatActivity(){

    @Inject
    lateinit var registerModel: RegisterModel

    override fun onCreate(savedInstanceState: Bundle?) {

        (application as MyApplication).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

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
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }


}