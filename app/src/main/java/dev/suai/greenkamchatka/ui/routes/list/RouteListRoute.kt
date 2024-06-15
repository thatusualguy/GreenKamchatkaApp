package dev.suai.greenkamchatka.ui.routes.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun RouteListRoute(
    zoneId: Int,
    onBackPressed: () -> Unit,
    onRouteSelected: (Int) -> Unit
) {
    val viewModel =
        hiltViewModel<RouteListViewModel, RouteListViewModel.RouteListViewModelFactory> { factory ->
            factory.create(zoneId)
        }
    val state = viewModel.uiState.collectAsState()

    RoutesListScreen(routes = state.value.routes, onBackPressed, onRouteSelected)
}