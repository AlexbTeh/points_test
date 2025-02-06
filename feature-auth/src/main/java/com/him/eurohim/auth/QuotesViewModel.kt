package com.him.eurohim.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.him.eurohim.domain.models.Quote
import com.him.eurohim.domain.usecases.GetRealtimeQuotes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuotesViewModel @Inject constructor(
    private val getRealtimeQuotes: GetRealtimeQuotes
) : ViewModel() {
    private val _quotesState = MutableStateFlow(QuotesState(isLoading = true))
    val quotesState = _quotesState.asStateFlow()

    init {
        subscribeToQuotes()
    }

    private fun subscribeToQuotes() {
        viewModelScope.launch {
            getRealtimeQuotes().catch { e ->
                _quotesState.value = _quotesState.value.copy(
                    isLoading = false,
                    error = e.message
                )
                delay(5000)
                subscribeToQuotes()
            }.collect { quotes ->
                _quotesState.value = QuotesState(quotes = quotes, isLoading = false)
            }
        }
    }
}


data class QuotesState(
    val quotes: Quote? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

