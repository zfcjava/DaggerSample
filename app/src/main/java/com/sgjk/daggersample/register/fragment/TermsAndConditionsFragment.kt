package com.sgjk.daggersample.register.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.sgjk.daggersample.R
import com.sgjk.daggersample.register.RegisterActivity
import com.sgjk.daggersample.register.RegisterModel

class TermsAndConditionsFragment : Fragment(){

    private lateinit var registerModel: RegisterModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_terms_and_conditions, container, false)

        registerModel = (activity as RegisterActivity).registerModel

        view.findViewById<Button>(R.id.next).setOnClickListener {
            registerModel.acceptTCs()
            (activity as RegisterActivity).onTermsAndConditionsAccepted()
        }
        return view
    }
}