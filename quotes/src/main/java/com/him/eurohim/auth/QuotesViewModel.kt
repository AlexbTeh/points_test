package com.him.eurohim.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.him.eurohim.domain.models.Quote
import com.him.eurohim.domain.usecases.GetRealtimeQuotes
import com.him.eurohim.domain.usecases.GetTopSecuritiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuotesViewModel @Inject constructor(
    private val getRealtimeQuotes: GetRealtimeQuotes,
    private val getTopSecuritiesUseCase: GetTopSecuritiesUseCase
) : ViewModel() {

    private val _quotesState = MutableStateFlow(QuotesState(isLoading = true))
    val quotesState = _quotesState.asStateFlow()

    init {
        subscribeToQuotes()
    }

    fun subscribeToQuotes() {
        viewModelScope.launch {
            getRealtimeQuotes()
                .catch { e ->
                    _quotesState.emit(_quotesState.value.copy(isLoading = false, error = e.message))
                }
                .collect { quotes ->
                    _quotesState.emit(_quotesState.value.copy(quotes = quotes.toList(), isLoading = false, error = null))
                }
        }
    }

    fun loadTopSecurities() {
        viewModelScope.launch(Dispatchers.IO) {
            _quotesState.update { it.copy(isLoading = true) }
            runCatching {
                getTopSecuritiesUseCase()
            }.onSuccess { topQuotes ->
                _quotesState.update {
                    it.copy(quotes = topQuotes, isLoading = false, error = null)
                }
            }.onFailure { error ->
                _quotesState.update {
                    it.copy(isLoading = false, error = error.localizedMessage)
                }
            }
        }
    }

}


data class QuotesState(
    val quotes: List<Quote>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

