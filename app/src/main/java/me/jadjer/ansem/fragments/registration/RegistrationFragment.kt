package me.jadjer.ansem.fragments.registration

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import me.jadjer.ansem.R
import me.jadjer.ansem.databinding.FragmentRegistrationBinding
import me.jadjer.ansem.utils.Event
import org.koin.android.ext.android.inject

class RegistrationFragment : Fragment() {

    private val registrationViewModel: RegistrationViewModel by inject()

    private lateinit var binding: FragmentRegistrationBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentRegistrationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = view.findNavController()

        val username = binding.registerEmail
        val firstName = binding.registerFirstName
        val lastName = binding.registerFirstName
        val password = binding.registerPassword
        val passwordAgain = binding.registerPasswordAgain
        val register = binding.registerButton
        val loading = binding.registerLoading

        registrationViewModel.registerFormState.observe(viewLifecycleOwner,
            Observer { registrationFormState ->
                if (registrationFormState == null) {
                    return@Observer
                }

                register.isEnabled = registrationFormState.isDataValid

                if (registrationFormState.usernameError != null) {
                    username.error = getString(registrationFormState.usernameError)
                }
                if (registrationFormState.firstNameError != null) {
                    firstName.error = getString(registrationFormState.firstNameError)
                }
                if (registrationFormState.lastNameError != null) {
                    lastName.error = getString(registrationFormState.lastNameError)
                }
                if (registrationFormState.passwordError != null) {
                    password.error = getString(registrationFormState.passwordError)
                }
                if (registrationFormState.passwordAgainError != null) {
                    passwordAgain.error = getString(registrationFormState.passwordAgainError)
                }
            })

        registrationViewModel.registrationEvent.observe(viewLifecycleOwner, { event ->
            when (event.status) {
                Event.Status.LOADING -> {
                    loading.visibility = View.VISIBLE
                }
                Event.Status.SUCCESS -> showLoginForm()
                Event.Status.ERROR -> {
                    showRegisterFailed(event.message)
                    loading.visibility = View.GONE
                }
            }
        })

        username.afterTextChanged {
            registrationViewModel.registerDataChanged(
                username.text.toString(),
                password.text.toString(),
                firstName.text.toString(),
                lastName.text.toString(),
                passwordAgain.text.toString()
            )
        }

        firstName.afterTextChanged {
            registrationViewModel.registerDataChanged(
                username.text.toString(),
                password.text.toString(),
                firstName.text.toString(),
                lastName.text.toString(),
                passwordAgain.text.toString()
            )
        }

        lastName.afterTextChanged {
            registrationViewModel.registerDataChanged(
                username.text.toString(),
                password.text.toString(),
                firstName.text.toString(),
                lastName.text.toString(),
                passwordAgain.text.toString()
            )
        }

        password.afterTextChanged {
            registrationViewModel.registerDataChanged(
                username.text.toString(),
                password.text.toString(),
                firstName.text.toString(),
                lastName.text.toString(),
                passwordAgain.text.toString()
            )
        }

        passwordAgain.apply {
            afterTextChanged {
                registrationViewModel.registerDataChanged(
                    username.text.toString(),
                    password.text.toString(),
                    firstName.text.toString(),
                    lastName.text.toString(),
                    passwordAgain.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        registrationViewModel.register(
                            username.text.toString(),
                            password.text.toString(),
                            firstName.text.toString(),
                            lastName.text.toString()
                        )
                }
                false
            }

            register.setOnClickListener {
                loading.visibility = View.VISIBLE
                registrationViewModel.register(
                    username.text.toString(),
                    password.text.toString(),
                    firstName.text.toString(),
                    lastName.text.toString()
                )
            }
        }
    }

    private fun showLoginForm() {
        navController.popBackStack()
    }

    private fun showRegisterFailed(error: String?) {
        Toast.makeText(activity, error, Toast.LENGTH_SHORT).show()
    }

    private fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                afterTextChanged.invoke(editable.toString())
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
    }
}