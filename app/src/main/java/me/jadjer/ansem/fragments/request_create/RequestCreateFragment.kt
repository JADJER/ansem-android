package me.jadjer.ansem.fragments.request_create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import me.jadjer.ansem.R
import me.jadjer.ansem.databinding.FragmentRequestCreateBinding
import me.jadjer.ansem.utils.Event
import org.koin.androidx.viewmodel.ext.android.viewModel

class RequestCreateFragment : Fragment() {

    private val _requestCreateViewModel: RequestCreateViewModel by viewModel()

    private lateinit var _binding: FragmentRequestCreateBinding
    private lateinit var _navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRequestCreateBinding.inflate(layoutInflater, container, false)
        _navController = findNavController()
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val school = _binding.requestCreateSchool
        val classNo = _binding.requestCreateClass
        val score = _binding.requestCreateScore
        val quest1 = _binding.requestCreateQuest1
        val quest2 = _binding.requestCreateQuest2
        val quest3 = _binding.requestCreateQuest3
        val quest4 = _binding.requestCreateQuest4
        val quest5 = _binding.requestCreateQuest5
        val quest6 = _binding.requestCreateQuest6
        val quest7 = _binding.requestCreateQuest7
        val sendButton = _binding.requestCreateButton
        val loading = _binding.requestCreateLoading

        _requestCreateViewModel.requestCreateFormState.observe(
            viewLifecycleOwner,
            Observer { formState ->
                if (formState == null) {
                    return@Observer
                }

                sendButton.isEnabled = formState.isDataValid

                if (formState.schoolError != null) {
                    school.error = getString(formState.schoolError)
                }

                if (formState.classError != null) {
                    classNo.error = getString(formState.classError)
                }

                if (formState.scoreError != null) {
                    score.error = getString(formState.scoreError)
                }
            })

        _requestCreateViewModel.requestCreateEvent.observe(viewLifecycleOwner, { event ->
            when (event.status) {
                Event.Status.LOADING -> {
                    loading.visibility = View.VISIBLE
                }
                Event.Status.SUCCESS -> {
                    showRequestsFragment()
                }
                Event.Status.ERROR -> {
                    showRequestCreateFailed(event.message)
                    loading.visibility = View.GONE
                }
            }
        })

        school.doAfterTextChanged {
            _requestCreateViewModel.dataChanged(
                school.text.toString(),
                classNo.text.toString(),
                score.text.toString()
            )
        }

        classNo.doAfterTextChanged {
            _requestCreateViewModel.dataChanged(
                school.text.toString(),
                classNo.text.toString(),
                score.text.toString()
            )
        }

        score.doAfterTextChanged {
            _requestCreateViewModel.dataChanged(
                school.text.toString(),
                classNo.text.toString(),
                score.text.toString()
            )
        }

        sendButton.setOnClickListener {
            var index = 0

            if (quest1.isChecked) { index++ }
            if (quest2.isChecked) { index++ }
            if (quest3.isChecked) { index++ }
            if (quest4.isChecked) { index++ }
            if (quest5.isChecked) { index++ }
            if (quest6.isChecked) { index++ }
            if (quest7.isChecked) { index++ }

            _requestCreateViewModel.createRequest(
                school.text.toString(),
                classNo.text.toString(),
                score.text.toString().toDouble(),
                index
            )
        }
    }

    private fun showRequestsFragment() {
        _navController.popBackStack()
    }

    private fun showRequestCreateFailed(error: String?) {
        Toast.makeText(activity, error, Toast.LENGTH_SHORT).show()
    }
}