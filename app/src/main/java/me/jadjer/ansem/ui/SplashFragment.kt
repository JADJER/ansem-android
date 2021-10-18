package me.jadjer.ansem.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import me.jadjer.ansem.R


class SplashFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_splash, container, false)

        val login = false
        if (login) {
            findNavController().navigate(R.id.action_splash_to_loginFragment)
        } else {
            findNavController().navigate(R.id.action_splash_to_requestFragment)
        }

        return view
    }
}