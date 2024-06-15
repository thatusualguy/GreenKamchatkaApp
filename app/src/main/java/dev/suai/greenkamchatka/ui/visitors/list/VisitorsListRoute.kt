package dev.suai.greenkamchatka.ui.visitors.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import dev.suai.greenkamchatka.ui.visitors.VisitorsViewModel

@Composable
fun VisitorsListRoute(
    onBackPress: () -> Unit,
    onAddPress: () -> Unit,
    onEditPress: (Int) -> Unit
) {
    val viewModel: VisitorsViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    VisitorsListScreen(
        visitors = uiState.visitors.map {
            Pair(
                it.id ?: -1,
                it.lastName + " " + it.firstName + " " + it.middleName + ", " + it.phone
            )
        }.toList(),
        onItemClick = { onEditPress(it) },
        onItemDelete = { viewModel.deleteVisitor(it) },
        onItemCreate = { onAddPress() }
    )
}