package me.jadjer.ansem.ui.authenticator

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import me.jadjer.ansem.R
import me.jadjer.ansem.databinding.ActivityAuthBinding


class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthBinding.inflate(layoutInflater)


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_auth) as NavHostFragment
        val navController = navHostFragment.navController

        when (intent.getStringExtra("auth_type")) {
            "login" -> navController.navigate(R.id.navigation_login)
            "registration" -> navController.navigate(R.id.navigation_registration)
        }

        setContentView(binding.root)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val result = Intent()

        val b = Bundle()
        result.putExtras(b)

        setResult(RESULT_OK, result)
        finish()
    }
}