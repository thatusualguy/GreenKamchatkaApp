package dev.suai.greenkamchatka.ui.zones

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.suai.greenkamchatka.data.Resource
import dev.suai.greenkamchatka.data.zones.Zone
import dev.suai.greenkamchatka.data.zones.ZonesRepository
import dev.suai.greenkamchatka.data.zones.impl.ZonesRepositoryHardcoded
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ZonesUiState(
    val zones: Resource<List<Zone>>,
    val clickedZone: Int? = null
)

@HiltViewModel
class ZonesViewModel @Inject constructor(private val zonesRepo: ZonesRepository) :
    ViewModel() {

    private val viewModelState = MutableStateFlow(ZonesUiState(Resource.loading()))
    val uiState: StateFlow<ZonesUiState>
        get() = viewModelState

    init {
        refreshZones()
    }


    private fun refreshZones() {
        viewModelState.update { it.copy(zones = Resource.loading()) }

        viewModelScope.launch(IO) {
            viewModelState.update { it.copy(zones = Resource.success(zonesRepo.getZones())) }
        }
    }

    fun clickOnZone(zoneId: Int) {
        viewModelState.update {
            it.copy(clickedZone = zoneId)
        }
    }
}