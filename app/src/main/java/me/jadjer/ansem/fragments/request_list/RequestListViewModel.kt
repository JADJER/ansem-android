package me.jadjer.ansem.fragments.request_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.jadjer.ansem.data.model.entity.RequestEntity
import me.jadjer.ansem.data.repository.AccountRepository
import me.jadjer.ansem.data.repository.AuthRepository
import me.jadjer.ansem.data.repository.RequestRepository
import me.jadjer.ansem.utils.Event
import java.util.ArrayList


class RequestListViewModel(
    private val authRepository: AuthRepository,
    private val accountRepository: AccountRepository,
    private val requestRepository: RequestRepository
) : ViewModel() {

    val updateEvent = MutableLiveData<Event<List<RequestEntity>>>()

    fun update() {
        updateEvent.value = Event.loading()

        viewModelScope.launch {
            try {
                val requests = requestRepository.getAll()
                updateEvent.value = Event.success(requests, "All done")

            } catch (exception: Exception) {
                updateEvent.value = Event.error("Update error")
            }
        }
    }

}