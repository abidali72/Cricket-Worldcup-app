package com.example.worldcupapp.presentation.tournament

import androidx.lifecycle.ViewModel
import com.example.worldcupapp.domain.model.PointTableEntry
import com.example.worldcupapp.domain.model.Team
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PointsTableViewModel @Inject constructor() : ViewModel() {

    private val _pointsTable = MutableStateFlow<List<PointTableEntry>>(emptyList())
    val pointsTable: StateFlow<List<PointTableEntry>> = _pointsTable.asStateFlow()

    init {
        loadMockData()
    }

    private fun loadMockData() {
        val india = Team("IND", "India", "IND", "https://flagcdn.com/w320/in.png", 1)
        val australia = Team("AUS", "Australia", "AUS", "https://flagcdn.com/w320/au.png", 2)
        val southAfrica = Team("SA", "South Africa", "SA", "https://flagcdn.com/w320/za.png", 3)
        val newZealand = Team("NZ", "New Zealand", "NZ", "https://flagcdn.com/w320/nz.png", 4)
        
        _pointsTable.value = listOf(
            PointTableEntry(india, 5, 5, 0, 0, 2.5f, 10),
            PointTableEntry(southAfrica, 5, 4, 1, 0, 2.1f, 8),
            PointTableEntry(newZealand, 5, 4, 1, 0, 1.5f, 8),
            PointTableEntry(australia, 5, 3, 2, 0, 0.9f, 6)
        )
    }
}
