package me.jadjer.ansem.data.repository

import android.accounts.Account
import android.accounts.AccountManager
import android.content.Context
import android.os.Bundle
import me.jadjer.ansem.data.ACCOUNT_TYPE
import me.jadjer.ansem.data.TOKEN_TYPE_ACCESS
import me.jadjer.ansem.data.model.entity.Request

class RequestRepositoryImpl(context: Context) : RequestRepository {

    private var accountManager: AccountManager = AccountManager.get(context)
    private var account: Account? = null
    private var token: String? = null

    override fun getAll(): List<Request> {
        TODO("Not yet implemented")
    }
}