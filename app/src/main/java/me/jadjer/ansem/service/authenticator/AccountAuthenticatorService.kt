package me.jadjer.ansem.service.authenticator

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log


class AccountAuthenticatorService : Service() {

    private lateinit var accountAuthenticator: AccountAuthenticator

    override fun onCreate() {
        super.onCreate()
        accountAuthenticator = AccountAuthenticator(this)
    }

    override fun onBind(intent: Intent): IBinder {
        return accountAuthenticator.iBinder
    }
}