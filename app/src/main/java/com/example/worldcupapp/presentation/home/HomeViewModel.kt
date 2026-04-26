package com.example.worldcupapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.worldcupapp.domain.model.Match
import com.example.worldcupapp.domain.repository.MatchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MatchRepository
) : ViewModel() {

    private val _matches = MutableStateFlow<List<Match>>(emptyList())
    val matches: StateFlow<List<Match>> = _matches.asStateFlow()

    init {
        fetchLiveMatches()
    }

    private fun fetchLiveMatches() {
        viewModelScope.launch {
            repository.getLiveMatches().collect { matchList ->
                _matches.value = matchList
            }
        }
    }
}
