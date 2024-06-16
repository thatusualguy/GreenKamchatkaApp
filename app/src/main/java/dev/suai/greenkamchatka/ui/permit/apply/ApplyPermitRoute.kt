package dev.suai.greenkamchatka.ui.permit.apply

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import dev.suai.greenkamchatka.ui.visitors.add.VisitorAddViewModel
import dev.suai.greenkamchatka.ui.visitors.add.VisitorParametersScreen

@Composable
fun ApplyPermitRoute(
    routeId: Int,
    onApply: () -> Unit,
) {

    val permitViewModel =
        hiltViewModel<ApplyPermitViewModel, ApplyPermitViewModel.ApplyPermitViewModelFactory> { factory ->
            factory.create(routeId)
        }
    val mainState by permitViewModel.uiState.collectAsState()

    val visitorViewModel =
        hiltViewModel<VisitorAddViewModel, VisitorAddViewModel.VisitorAddViewModelFactory> { factory ->
            factory.create(null)
        }

    val visitorState by visitorViewModel.uiState.collectAsState()
    permitViewModel.changeVisitor(visitorState.visitor)

    ApplyPermitScreen(mainState,
        onApplyForPermit = {
            permitViewModel.onApplyForPermit()
            onApply()
        },
        onSavedSelect = { permitViewModel.onSavedSelect(it) },
        onDateChange = {permitViewModel.onDateChange(it)},
        onReasonChange = {permitViewModel.onReasonChange(it)},
        content = {
            VisitorParametersScreen(
                visitor = mainState.visitor,
                onFirstNameChange = { visitorViewModel.onFirstNameChange(it) },
                onMiddleNameChange = { visitorViewModel.onMiddleNameChange(it) },
                onLastNameChange = { visitorViewModel.onLastNameChange(it) },
                onEmailChange = { visitorViewModel.onEmailChange(it) },
                onPhoneChange = { visitorViewModel.onPhoneChange(it) },
                onDobChange = { visitorViewModel.onDobChange(it) },
                onGenderChange = { visitorViewModel.onGenderChange(it) },
                onCitizenshipChange = { visitorViewModel.onCitizenshipChange(it) },
                onRegRegionChange = { visitorViewModel.onRegRegionChange(it) },
                onPassportSeriesChange = { visitorViewModel.onPassportSeriesChange(it) },
                onPassportNumChange = { visitorViewModel.onPassportNumChange(it) },
            )
        }
    )


}