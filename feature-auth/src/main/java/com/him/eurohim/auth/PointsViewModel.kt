package com.him.eurohim.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.him.eurohim.domain.models.Point
import com.him.eurohim.domain.usecases.GetPointsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PointsViewModel @Inject constructor(
    private val getPointsUseCase: GetPointsUseCase
): ViewModel(){
    private val _uiState = MutableStateFlow<UiState>(UiState.None)
    val uiState get() = _uiState.asStateFlow()

    fun fetchPoints(count: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = UiState.Loading
            val result = getPointsUseCase(count)
            when {
                result.isSuccess -> {
                    _uiState.value = UiState.Success(result.getOrNull() ?: emptyList())
                }
                result.isFailure -> {
                    val errorMessage = result.exceptionOrNull()?.message ?: "Unknown Error"
                    _uiState.value = UiState.Error(errorMessage)
                }
            }
        }
    }
}

sealed interface UiState{
    data object Loading : UiState
    data class Success(val points: List<Point>): UiState
    data class Error(val message: String) : UiState
    data object None : UiState
}
