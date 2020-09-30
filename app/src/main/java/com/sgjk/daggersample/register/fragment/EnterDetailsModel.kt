package com.sgjk.daggersample.register.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sgjk.daggersample.scope.ActivityScope
import javax.inject.Inject

private const val MAX_LENGTH = 5
//TODO 这里需要吗
@ActivityScope
class EnterDetailsModel @Inject constructor(){

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