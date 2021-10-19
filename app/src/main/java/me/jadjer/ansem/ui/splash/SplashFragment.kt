package me.jadjer.ansem.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.navigation.fragment.findNavController
import me.jadjer.ansem.R

class SplashFragment : Fragment() {

    private val viewModel: SplashViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_splash, container, false)

        val isAuth = viewModel.isAuth()
        if (isAuth) {
            findNavController().navigate(R.id.action_splash_to_requestFragment)
        } else {
            findNavController().navigate(R.id.action_splash_to_loginFragment)
        }

        return view
    }
}