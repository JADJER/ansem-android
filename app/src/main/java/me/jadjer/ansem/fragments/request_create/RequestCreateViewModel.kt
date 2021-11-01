package me.jadjer.ansem.fragments.request_create

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.jadjer.ansem.R
import me.jadjer.ansem.data.repository.RequestRepository
import me.jadjer.ansem.utils.Event
import me.jadjer.ansem.utils.RequestCreateFormState
import java.lang.Exception

class RequestCreateViewModel(private val requestRepository: RequestRepository) : ViewModel() {

    val requestCreateFormState = MutableLiveData<RequestCreateFormState>()
    val requestCreateEvent = MutableLiveData<Event<Boolean>>()

    fun createRequest(school: String, classNo: String, score: Double, index: Int) {
        viewModelScope.launch {
            try {
                val result = requestRepository.create(school, classNo, score, index, 1)
                requestCreateEvent.value = Event.success(result)

            } catch (exception: Exception) {
                requestCreateEvent.value = Event.error("Internal server error")
            }
        }
    }

    fun dataChanged(school: String, classNo: String, score: String) {
        if (isFieldEmpty(school)) {
            requestCreateFormState.value = RequestCreateFormState(schoolError = R.string.invalid_school)
            return
        }

        if (isFieldEmpty(classNo)) {
            requestCreateFormState.value = RequestCreateFormState(classError = R.string.invalid_class)
            return
        }

        if (isFieldEmpty(score)) {
            requestCreateFormState.value = RequestCreateFormState(scoreError = R.string.invalid_score)
            return
        }

        if (!isScoreDigit(score)) {
            requestCreateFormState.value = RequestCreateFormState(scoreError = R.string.invalid_score_value)
            return
        }

        if (!isScoreValid(score.toDouble())) {
            requestCreateFormState.value = RequestCreateFormState(scoreError = R.string.invalid_score_value)
            return
        }

        requestCreateFormState.value = RequestCreateFormState(isDataValid = true)
    }

    private fun isFieldEmpty(field: String): Boolean {
        return field.isEmpty()
    }

    private fun isScoreDigit(field: String): Boolean {
        field.toDoubleOrNull() ?: return false; return true
    }

    private fun isScoreValid(score: Double): Boolean {
        return score in 0.0..10.0
    }

}