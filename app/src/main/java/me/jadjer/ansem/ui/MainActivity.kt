package me.jadjer.ansem.ui

import android.accounts.AccountManager
import android.accounts.AccountManagerCallback
import android.accounts.AccountManagerFuture
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import me.jadjer.ansem.R
import me.jadjer.ansem.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment

        navController = navHostFragment.navController
        navController.setGraph(R.navigation.main_navigation)

        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        val navView: BottomNavigationView = binding.navView
        navView.setupWithNavController(navController)

        val am = AccountManager.get(this)
        am.getAuthTokenByFeatures(
            "me.jadjer.motoecu",
            "access",
            null,
            this,
            null,
            null,
            GetAuthTokenCallback(),
            null
        )
        Log.d("Main", "4")
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onResume() {
        Log.d("Main", "5")
        val am = AccountManager.get(this)
        am.getAuthTokenByFeatures(
            "me.jadjer.motoecu",
            "access",
            null,
            this,
            null,
            null,
            GetAuthTokenCallback(),
            null
        )
        Log.d("Main", "4")

        super.onResume()
    }

    private class GetAuthTokenCallback : AccountManagerCallback<Bundle> {
        override fun run(future: AccountManagerFuture<Bundle>) {
            Log.d("AuthCallback", "1")
            var bnd: Bundle? = null
            Log.d("AuthCallback", "2")
            try {
                Log.d("AuthCallback", "3")
                if (future.isCancelled) {
                    Log.d("AuthCallback", "4")
                    // Do whatever you want. I understand that you want to close this activity,
                    // so supposing that mActivity is your activity:
//                    mActivity.finish()
                    Log.d("AuthCallback", "5")
                    return
                }
                Log.d("AuthCallback", "6")

                bnd = future.result
                val authtoken = bnd.getString(AccountManager.KEY_AUTHTOKEN)

                Log.d("AuthCallback", "GetTokenForAccount Bundle is $bnd")

            } catch (e: Exception) {
                Log.d("AuthCallback", "exception while getAuthTokenByFeatures", e)
            }
        }
    }
}