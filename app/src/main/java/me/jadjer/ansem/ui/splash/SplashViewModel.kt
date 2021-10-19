package me.jadjer.ansem.ui.splash

import android.content.Context
import android.content.pm.PackageManager
import androidx.lifecycle.ViewModel

class SplashViewModel(private val context: Context) : ViewModel() {

    private fun PackageManager.missingSystemFeature(name: String): Boolean = !hasSystemFeature(name)

    fun checkSupportBLE() : Boolean {
        val packageManager = context.packageManager

        if (packageManager.missingSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            return false
        }

        return true
    }
}