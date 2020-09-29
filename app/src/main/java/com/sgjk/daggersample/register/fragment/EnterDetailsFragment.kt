package com.sgjk.daggersample.register.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.sgjk.daggersample.R
import com.sgjk.daggersample.register.RegisterActivity
import com.sgjk.daggersample.register.RegisterModel

class EnterDetailsFragment : Fragment(){

    private lateinit var registerModel: RegisterModel
    private lateinit var enterDetailsModel:EnterDetailsModel


    private lateinit var errorTextView: TextView
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_enter_details, container, false)

        registerModel = (activity as RegisterActivity).registerModel
        enterDetailsModel = EnterDetailsModel()

        //注册观察者
        enterDetailsModel.enterUserState.observe(this,Observer<EnterDetailsViewState>{
            when(it){
                is EnterDetailsSuccess -> {

                    val username = usernameEditText.text.toString()
                    val password = passwordEditText.text.toString()
                    registerModel.updateUserData(username, password)

                    (activity as RegisterActivity).onDetailsEntered()
                }
                is EnterDetailsError -> {
                    errorTextView.text = it.msg
                    errorTextView.visibility = View.VISIBLE
                }
            }

        })

        setupViews(view)

        return view
    }

    private fun setupViews(view: View) {
        errorTextView = view.findViewById(R.id.error)

        usernameEditText = view.findViewById(R.id.username)
        usernameEditText.doOnTextChanged { _, _, _, _ -> errorTextView.visibility = View.INVISIBLE }

        passwordEditText = view.findViewById(R.id.password)
        passwordEditText.doOnTextChanged { _, _, _, _ -> errorTextView.visibility = View.INVISIBLE }

        view.findViewById<Button>(R.id.next).setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            enterDetailsModel.validateInput(username, password)
        }
    }

}




sealed class EnterDetailsViewState

object EnterDetailsSuccess:EnterDetailsViewState()

data class EnterDetailsError(val msg:String):EnterDetailsViewState()
