package com.sgjk.daggersample.register

import android.app.Application
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sgjk.daggersample.MyApplication
import com.sgjk.daggersample.R
import com.sgjk.daggersample.di.RegisterComponent
import com.sgjk.daggersample.main.MainActivity
import com.sgjk.daggersample.register.fragment.EnterDetailsFragment
import com.sgjk.daggersample.register.fragment.TermsAndConditionsFragment
import javax.inject.Inject

class RegisterActivity : AppCompatActivity(){

    @Inject
    lateinit var registerModel: RegisterModel

    // Stores an instance of RegistrationComponent so that its Fragments can access it
    lateinit var registerComponent: RegisterComponent

    override fun onCreate(savedInstanceState: Bundle?) {

        registerComponent = (application as MyApplication).appComponent.registerComponent().create();
        registerComponent.inject(this)

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