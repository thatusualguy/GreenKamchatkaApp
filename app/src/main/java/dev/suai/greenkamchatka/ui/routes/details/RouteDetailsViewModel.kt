package dev.suai.greenkamchatka.ui.routes.details

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


data class RouteDetailsState(val routeId: Int, val route: Route)

@HiltViewModel(assistedFactory = RouteDetailsViewModel.RouteDetailsViewModelFactory::class)
class RouteDetailsViewModel @AssistedInject constructor(
    @Assisted val routeId: Int,
    private val routeRepo: RouteRepository
) : ViewModel() {

    @AssistedFactory
    interface RouteDetailsViewModelFactory {
        fun create(id: Int): RouteDetailsViewModel
    }

    private val viewModelState = MutableStateFlow(RouteDetailsState(routeId, Route()))
    val uiState: StateFlow<RouteDetailsState>
        get() = viewModelState

    init {
        getRoute()
    }

    private fun getRoute() {
        viewModelScope.launch(Dispatchers.IO) {
            viewModelState.update {
                it.copy(route = routeRepo.getRoutes().find { it.id == routeId } ?: Route())
            }
        }
    }
}