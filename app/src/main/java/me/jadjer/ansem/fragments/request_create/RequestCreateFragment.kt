package me.jadjer.ansem.fragments.request_create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.jadjer.ansem.databinding.FragmentRequestCreateBinding

class RequestCreateFragment : Fragment() {

    private lateinit var _binding: FragmentRequestCreateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRequestCreateBinding.inflate(layoutInflater, container, false)
        return _binding.root
    }

}