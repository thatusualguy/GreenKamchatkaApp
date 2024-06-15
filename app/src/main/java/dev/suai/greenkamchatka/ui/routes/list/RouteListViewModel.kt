package dev.suai.greenkamchatka.ui.routes.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.suai.greenkamchatka.data.routes.Route
import dev.suai.greenkamchatka.data.routes.RouteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class RouteListState(val zoneId: Int, val routes: List<Route>)

@HiltViewModel(assistedFactory = RouteListViewModel.RouteListViewModelFactory::class)
class RouteListViewModel @AssistedInject constructor(
    @Assisted val zoneId: Int,
    private val routeRepo: RouteRepository
) : ViewModel() {

    @AssistedFactory
    interface RouteListViewModelFactory {
        fun create(id: Int): RouteListViewModel
    }

    private val viewModelState = MutableStateFlow(RouteListState(zoneId, emptyList()))
    val uiState: StateFlow<RouteListState>
        get() = viewModelState

    init {
        getRoutes()
    }

    private fun getRoutes() {
        viewModelScope.launch(Dispatchers.IO) {
            viewModelState.update { it.copy(routes = routeRepo.getRoutes(zoneId)) }
        }
    }
}