package ni.devotion.mvvm.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.request.Request
import kotlinx.coroutines.launch
import ni.devotion.mvvm.R
import ni.devotion.mvvm.data.network.`interface`.BusinessInterface
import ni.devotion.mvvm.model.BusinessList
import ni.devotion.mvvm.repo.BusinessRepository

class BusinessViewModel (private val businessRepository: BusinessRepository): ViewModel() {
    private val _uiState = MutableLiveData<BusinessDataState>()
    val uiState: LiveData<BusinessDataState> get() = _uiState

    init {
        println("Pasa por el DEPARTMENTVIEW_MODEL")
        retrieveBusiness()
    }


    fun retrieveBusiness(){
        println("Pasa por el retrieve Business")
        viewModelScope.launch {
                runCatching {
                    emitUiState(showProgress = true)
                    businessRepository.getBusiness()
                }.onSuccess {
                    emitUiState(business = Event(it))
                }.onFailure {
                    println("ESTO: $it")
                    emitUiState(error = Event(R.string.internet_failure_error))
                }
        }
    }

    private fun emitUiState(showProgress: Boolean = false, business: Event<List<BusinessList>>? = null, error: Event<Int>? = null){
        val dataState = BusinessDataState(showProgress, business, error)
        _uiState.value = dataState
    }

    data class BusinessDataState(val showProgress: Boolean, val business: Event<List<BusinessList>>?, val error: Event<Int>?)
}