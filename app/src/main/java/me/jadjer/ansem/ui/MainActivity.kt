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
import me.jadjer.ansem.R
import me.jadjer.ansem.data.AccountGeneral
import me.jadjer.ansem.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var accountManager: AccountManager

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Ansem)
        super.onCreate(savedInstanceState)

        accountManager = AccountManager.get(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment

        val navView: BottomNavigationView = binding.navView

        navController = navHostFragment.navController
        navController.setGraph(R.navigation.nav_graph)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.loginFragment || destination.id == R.id.registrationFragment) {
                navView.visibility = View.GONE
            } else {
                navView.visibility = View.VISIBLE
            }
        }

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.requestListFragment,
                R.id.loginFragment,
                R.id.userProfileFragment
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)

        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    /**
     * Return to login fragment if account deleted from system
     */
    override fun onStart() {
        super.onStart()

        val accounts = accountManager.getAccountsByType(AccountGeneral.ACCOUNT_TYPE)
        if (accounts.isEmpty()) {
            navController.navigate(R.id.action_global_loginFragment)
        }
    }
}