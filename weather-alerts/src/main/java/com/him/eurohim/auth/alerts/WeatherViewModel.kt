package com.him.eurohim.auth.alerts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.him.eurohim.domain.models.WeatherAlert
import com.him.eurohim.domain.usecases.GetWeatherAlertsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val useCase: GetWeatherAlertsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(WeatherState(isLoading = true))
    val state: StateFlow<WeatherState> = _state.asStateFlow()

    init {
        getAlerts()
    }

    private fun getAlerts() {
        viewModelScope.launch {
            try {
                val alerts = useCase.execute()
                _state.update { it.copy(alerts = alerts, isLoading = false) }
            } catch (e: Exception) {
                _state.update { it.copy(error = e.localizedMessage ?: "Unexpected error", isLoading = false) }
            }
        }
    }
}


data class WeatherState(
    val alerts: List<WeatherAlert> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedAlert: WeatherAlert? = null
)


