package ni.devotion.mvvm.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ni.devotion.mvvm.R
import ni.devotion.mvvm.model.Categories
import ni.devotion.mvvm.repo.CategoriesRepository

class CategoriesViewModel (private val categoriesRepository: CategoriesRepository): ViewModel() {
    private val _uiState = MutableLiveData<CategoriesDataState>()
    val uiState: LiveData<CategoriesDataState> get() = _uiState

    init {
        retrieveCategories()
    }

    fun retrieveCategories(){
        viewModelScope.launch {
            runCatching {
                emitUiState(showProgress = true)
                categoriesRepository.getCategories()
            }.onSuccess {
                println("SUCCCCEEESS: $it")
                emitUiState(categories = Event(it))
            }.onFailure {
                emitUiState(error = Event(R.string.internet_failure_error))
                println("ERRORORRR: $it")
            }
        }
    }

    private fun emitUiState(showProgress: Boolean = false, categories: Event<List<Categories>>? = null, error: Event<Int>? = null){
        val dataState = CategoriesDataState(showProgress, categories, error)
        _uiState.value = dataState
    }

    data class CategoriesDataState(val showProgress: Boolean, val categories: Event<List<Categories>>?, val error: Event<Int>?)
}