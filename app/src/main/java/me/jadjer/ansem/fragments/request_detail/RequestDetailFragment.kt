package me.jadjer.ansem.fragments.request_detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.jadjer.ansem.R

class RequestDetailFragment : Fragment() {

    companion object {
        fun newInstance() = RequestDetailFragment()
    }

    private lateinit var viewModel: RequestDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_request_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RequestDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}