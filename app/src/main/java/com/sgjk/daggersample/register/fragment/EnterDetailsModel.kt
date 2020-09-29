package com.sgjk.daggersample.register.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

private const val MAX_LENGTH = 5

class EnterDetailsModel {

    private val _enterUserState = MutableLiveData<EnterDetailsViewState>()

    val enterUserState : LiveData<EnterDetailsViewState>
        get() = _enterUserState

    fun validateInput(username: String, password: String) {
        when {
            username.length < MAX_LENGTH ->
                    _enterUserState.value = EnterDetailsError("Username has to be longer than 4 characters")
            password.length < MAX_LENGTH ->
                    _enterUserState.value = EnterDetailsError("Password has to be longer than 4 characters")

            else ->
                    _enterUserState.value = EnterDetailsSuccess
        }
    }
}