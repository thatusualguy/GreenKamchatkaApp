package dev.suai.greenkamchatka.ui.permit.select

import androidx.compose.runtime.Composable

@Composable
fun PermitTypeRoute(
    onIndividualClick: () -> Unit,
    onGroupClick: () -> Unit,
    onCompanyClick: () -> Unit,
) {
    PermitTypeScreen(
        onIndividualClick = onIndividualClick,
        onGroupClick = onGroupClick,
        onCompanyClick = onCompanyClick
    )
}