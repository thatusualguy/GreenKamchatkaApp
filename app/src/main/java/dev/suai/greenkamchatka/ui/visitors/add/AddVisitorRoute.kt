package dev.suai.greenkamchatka.ui.visitors.add

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun CreateVisitorRoute(onBackPress: () -> Unit,
                     onSavePress: () -> Unit){
    AddVisitorRoute(onBackPress, onSavePress, null)
}


@Composable
fun EditVisitorRoute(onBackPress: () -> Unit,
                       onSavePress: () -> Unit,
                     visitorId:Int)
{
    AddVisitorRoute(onBackPress, onSavePress, visitorId)
}

@Composable
private fun AddVisitorRoute(
    onBackPress: () -> Unit,
    onSavePress: () -> Unit,
    visitorId:Int? = null
) {
    val viewModel =
        hiltViewModel<VisitorAddViewModel, VisitorAddViewModel.VisitorAddViewModelFactory> { factory ->
            factory.create(visitorId)
        }

    val uiState by viewModel.uiState.collectAsState()

    AddVisitorScreen(
        visitor = uiState.visitor,
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

