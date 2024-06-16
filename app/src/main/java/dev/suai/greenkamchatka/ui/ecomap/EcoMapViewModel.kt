package dev.suai.greenkamchatka.ui.ecomap

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.suai.greenkamchatka.data.map.EcoMapRepository
import dev.suai.greenkamchatka.data.map.ReportMarker
import dev.suai.greenkamchatka.ui.visitors.list.VisitorsListState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


data class EcoMapState(val reports: List<ReportMarker> = emptyList())

@HiltViewModel
class EcoMapViewModel @Inject constructor(private val ecoMapRepo: EcoMapRepository) : ViewModel() {

    private val viewModelState = MutableStateFlow(EcoMapState())
    val uiState: StateFlow<EcoMapState>
        get() = viewModelState

    init {
        viewModelScope.launch {
            ecoMapRepo.getAll().collect { reports ->
                viewModelState.update { it.copy(reports = reports) }
            }
        }
    }
}