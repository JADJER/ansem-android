package me.jadjer.ansem.ui.request_list

import android.os.Bundle
import androidx.lifecycle.ViewModel
import me.jadjer.ansem.data.repository.AccountRepository


class RequestListViewModel(private val repo: AccountRepository) : ViewModel() {

    fun isAuth(): Boolean {
        val userData = Bundle()
        userData.putString("UserID", "25")

//        repo.registration("jadjer", "Alchogol01", userData)

        val account = repo.get()

        return account != null
    }
}