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

        val email = binding.registerEmail
        val firstName = binding.registerFirstName
        val lastName = binding.registerFirstName
        val country = binding.registerCountry
        val city = binding.registerCity
        val address = binding.registerAddress
        val mobileNo = binding.registerMobileNo
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

                if (registrationFormState.emailError != null) {
                    email.error = getString(registrationFormState.emailError)
                }
                if (registrationFormState.firstNameError != null) {
                    firstName.error = getString(registrationFormState.firstNameError)
                }
                if (registrationFormState.lastNameError != null) {
                    lastName.error = getString(registrationFormState.lastNameError)
                }
                if (registrationFormState.countryError != null) {
                    country.error = getString(registrationFormState.countryError)
                }
                if (registrationFormState.cityError != null) {
                    city.error = getString(registrationFormState.cityError)
                }
                if (registrationFormState.addressError != null) {
                    address.error = getString(registrationFormState.addressError)
                }
                if (registrationFormState.mobileNoError != null) {
                    mobileNo.error = getString(registrationFormState.mobileNoError)
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

        email.afterTextChanged {
            registrationViewModel.registerDataChanged(
                email.text.toString(),
                firstName.text.toString(),
                lastName.text.toString(),
                country.text.toString(),
                city.text.toString(),
                address.text.toString(),
                mobileNo.text.toString(),
                password.text.toString(),
                passwordAgain.text.toString()
            )
        }

        firstName.afterTextChanged {
            registrationViewModel.registerDataChanged(
                email.text.toString(),
                firstName.text.toString(),
                lastName.text.toString(),
                country.text.toString(),
                city.text.toString(),
                address.text.toString(),
                mobileNo.text.toString(),
                password.text.toString(),
                passwordAgain.text.toString()
            )
        }

        lastName.afterTextChanged {
            registrationViewModel.registerDataChanged(
                email.text.toString(),
                firstName.text.toString(),
                lastName.text.toString(),
                country.text.toString(),
                city.text.toString(),
                address.text.toString(),
                mobileNo.text.toString(),
                password.text.toString(),
                passwordAgain.text.toString()
            )
        }

        country.afterTextChanged {
            registrationViewModel.registerDataChanged(
                email.text.toString(),
                firstName.text.toString(),
                lastName.text.toString(),
                country.text.toString(),
                city.text.toString(),
                address.text.toString(),
                mobileNo.text.toString(),
                password.text.toString(),
                passwordAgain.text.toString()
            )
        }

        city.afterTextChanged {
            registrationViewModel.registerDataChanged(
                email.text.toString(),
                firstName.text.toString(),
                lastName.text.toString(),
                country.text.toString(),
                city.text.toString(),
                address.text.toString(),
                mobileNo.text.toString(),
                password.text.toString(),
                passwordAgain.text.toString()
            )
        }

        address.afterTextChanged {
            registrationViewModel.registerDataChanged(
                email.text.toString(),
                firstName.text.toString(),
                lastName.text.toString(),
                country.text.toString(),
                city.text.toString(),
                address.text.toString(),
                mobileNo.text.toString(),
                password.text.toString(),
                passwordAgain.text.toString()
            )
        }

        mobileNo.afterTextChanged {
            registrationViewModel.registerDataChanged(
                email.text.toString(),
                firstName.text.toString(),
                lastName.text.toString(),
                country.text.toString(),
                city.text.toString(),
                address.text.toString(),
                mobileNo.text.toString(),
                password.text.toString(),
                passwordAgain.text.toString()
            )
        }

        password.afterTextChanged {
            registrationViewModel.registerDataChanged(
                email.text.toString(),
                firstName.text.toString(),
                lastName.text.toString(),
                country.text.toString(),
                city.text.toString(),
                address.text.toString(),
                mobileNo.text.toString(),
                password.text.toString(),
                passwordAgain.text.toString()
            )
        }

        passwordAgain.apply {
            afterTextChanged {
                registrationViewModel.registerDataChanged(
                    email.text.toString(),
                    firstName.text.toString(),
                    lastName.text.toString(),
                    country.text.toString(),
                    city.text.toString(),
                    address.text.toString(),
                    mobileNo.text.toString(),
                    password.text.toString(),
                    passwordAgain.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        registrationViewModel.register(
                            email.text.toString(),
                            firstName.text.toString(),
                            lastName.text.toString(),
                            country.text.toString(),
                            city.text.toString(),
                            address.text.toString(),
                            mobileNo.text.toString(),
                            password.text.toString()
                        )
                }
                false
            }

            register.setOnClickListener {
                loading.visibility = View.VISIBLE
                registrationViewModel.register(
                    email.text.toString(),
                    firstName.text.toString(),
                    lastName.text.toString(),
                    country.text.toString(),
                    city.text.toString(),
                    address.text.toString(),
                    mobileNo.text.toString(),
                    password.text.toString()
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