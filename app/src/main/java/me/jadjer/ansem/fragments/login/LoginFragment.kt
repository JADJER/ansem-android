package me.jadjer.ansem.fragments.login

import android.accounts.AccountManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import me.jadjer.ansem.R
import me.jadjer.ansem.databinding.FragmentLoginBinding
import me.jadjer.ansem.utils.Event
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val loginViewModel: LoginViewModel by viewModel()

    private lateinit var binding: FragmentLoginBinding
    private lateinit var navController: NavController
    private lateinit var accountManager: AccountManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        accountManager = AccountManager.get(context)
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        navController = findNavController()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = binding.loginUsername
        val password = binding.loginPassword
        val login = binding.loginButton
        val loading = binding.loginLoading
        val signup = binding.textView2

        loginViewModel.loginFormState.observe(viewLifecycleOwner, Observer { loginFormState ->
            if (loginFormState == null) {
                return@Observer
            }

            login.isEnabled = loginFormState.isDataValid

            if (loginFormState.usernameError != null) {
                username.error = getString(loginFormState.usernameError)
            }
            if (loginFormState.passwordError != null) {
                password.error = getString(loginFormState.passwordError)
            }
        })

        loginViewModel.loginEvent.observe(viewLifecycleOwner, { event ->
            when (event.status) {
                Event.Status.LOADING -> {
                    loading.visibility = View.VISIBLE
                }
                Event.Status.SUCCESS -> {
                    showRequestsFragment()
                }
                Event.Status.ERROR -> {
                    showLoginFailed(event.message)
                    loading.visibility = View.GONE
                }
            }
        })

        loginViewModel.checkAccount()

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(password.text.toString())
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            username.text.toString(),
                            password.text.toString()
                        )
                }
                false
            }

            login.setOnClickListener {
                loginViewModel.login(username.text.toString(), password.text.toString())
            }
        }

        signup.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_registrationFragment)
        }
    }

    private fun showRequestsFragment() {
        navController.navigate(R.id.action_loginFragment_to_requestListFragment)
    }

    private fun showLoginFailed(error: String?) {
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