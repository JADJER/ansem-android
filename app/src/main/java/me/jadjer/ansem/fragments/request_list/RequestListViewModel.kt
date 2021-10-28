package me.jadjer.ansem.fragments.request_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.jadjer.ansem.data.repository.RequestRepository


class RequestListViewModel(private val requestRepository: RequestRepository) : ViewModel() {

    fun test() {
        viewModelScope.launch {
            try {
                requestRepository.getAll()
            } catch (exception: Exception) {

            }
        }
    }

}