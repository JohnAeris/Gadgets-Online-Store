package com.example.gadgetsonlinestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_get_started_screen.view.*
import kotlinx.android.synthetic.main.fragment_login_screen.view.*

class LoginScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login_screen, container, false)

        //Button to navigate to Landing Page Screen
        view.tvLogin.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigateToGetStartedScreen)
        }

        return view
    }
}