package com.example.gadgetsonlinestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.gadgetsonlinestore.databinding.FragmentGetStartedScreenBinding


class GetStartedScreen : Fragment() {

    private lateinit var binding: FragmentGetStartedScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentGetStartedScreenBinding.inflate(inflater, container, false)
        val view = binding.root

        //Button to navigate to Landing Page Screen
        binding.btnGetStarted.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigateToLandingPageScreen)
        }

        //Button to navigate to Login Screen
        binding.btnSkip.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigateToLoginScreen)
        }

        return view
    }
}