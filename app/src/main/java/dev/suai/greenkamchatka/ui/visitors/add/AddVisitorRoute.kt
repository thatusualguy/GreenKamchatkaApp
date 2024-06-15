package dev.suai.greenkamchatka.ui.visitors.add

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import dev.suai.greenkamchatka.ui.visitors.VisitorsViewModel

@Composable
fun AddVisitorRoute(
    onBackPress: () -> Unit,
    onSavePress: () -> Unit,
    visitorId:Int? = null
) {
    val viewModel: VisitorsViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(visitorId) {
        viewModel.selectVisitor(visitorId)
    }

    AddVisitorScreen(
        visitor = uiState.selectedVisitor,
        onBackPress = onBackPress,
        onSavePress = {
            viewModel.insertVisitor()
            onSavePress()
        },
        onFirstNameChange = { viewModel.onFirstNameChange(it) },
        onMiddleNameChange = { viewModel.onMiddleNameChange(it) },
        onLastNameChange = { viewModel.onLastNameChange(it) },
        onEmailChange = { viewModel.onEmailChange(it) },
        onPhoneChange = { viewModel.onPhoneChange(it) },
        onDobChange = { viewModel.onDobChange(it) },
        onGenderChange = { viewModel.onGenderChange(it) },
        onCitizenshipChange = { viewModel.onCitizenshipChange(it) },
        onRegRegionChange = { viewModel.onRegRegionChange(it) },
        onPassportSeriesChange = { viewModel.onPassportSeriesChange(it) },
        onPassportNumChange = { viewModel.onPassportNumChange(it) },
    )
}

