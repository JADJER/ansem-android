package me.jadjer.ansem.ui

import android.accounts.AccountManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import me.jadjer.ansem.AccountGeneral
import me.jadjer.ansem.R
import me.jadjer.ansem.databinding.ActivityMainBinding
import me.jadjer.ansem.service.authenticator.AccountAuthenticator


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var accountManager: AccountManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        accountManager = AccountManager.get(this)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment

        val navView: BottomNavigationView = binding.navView

        navController = navHostFragment.navController
        navController.setGraph(R.navigation.main_navigation)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.label == "fragment_registration" || destination.label == "fragment_login") {
                navView.visibility = View.GONE
            } else {
                navView.visibility = View.VISIBLE
            }
        }

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.requestListFragment, R.id.loginFragment)
        )

        setupActionBarWithNavController(navController, appBarConfiguration)

        navView.setupWithNavController(navController)


        /**
         *
         */
        val authType = intent.getStringExtra(AccountAuthenticator.AUTH_TYPE)

        if (authType == AccountAuthenticator.AUTH_TYPE_LOGIN) {
            navController.navigate(R.id.action_splashFragment_to_loginFragment, intent.extras)
            return
        }

        if (authType == AccountAuthenticator.AUTH_TYPE_REGISTER) {
            navController.navigate(R.id.action_splashFragment_to_registrationFragment, intent.extras)
            return
        }

        accountManager.getAuthTokenByFeatures(
            AccountGeneral.ACCOUNT_TYPE,
            AccountGeneral.AUTHTOKEN_TYPE_FULL_ACCESS,
            null,
            this,
            null,
            null,
            null,
            null
        )

        navController.navigate(R.id.action_splashFragment_to_requestListFragment, intent.extras)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}