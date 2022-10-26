package com.example.gadgetsonlinestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_get_started_screen.view.*


class GetStartedScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_get_started_screen, container, false)

        //Button to navigate to Landing Page Screen
        view.btnGetStarted.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigateToLandingPageScreen)
        }

        //Button to navigate to Login Screen
        view.btnSkip.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigateToLoginScreen)
        }

        return view
    }
}