package dev.suai.greenkamchatka.ui.routes.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun RouteDetailsRoute(routeId: Int, onBackPressed: () -> Unit, onRouteBook: (Int) -> Unit) {


    val viewModel =
        hiltViewModel<RouteDetailsViewModel, RouteDetailsViewModel.RouteDetailsViewModelFactory> { factory ->
            factory.create(routeId)
        }
    val state = viewModel.uiState.collectAsState()

    RouteDetailsScreen(route = state.value.route, onRouteBook, onBackPressed)
}