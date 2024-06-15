package dev.suai.greenkamchatka.ui.permit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.suai.greenkamchatka.ui.components.ButtonWithHint
import dev.suai.greenkamchatka.ui.theme.GreenKamchatkaTheme

@Composable
fun PermitTypeScreen(
    onIndividualClick: () -> Unit,
    onGroupClick: () -> Unit,
    onCompanyClick: () -> Unit,
) {

    val buttons = listOf(
        Triple(
            "Индивидуальные",
            "действует до 30 суток",
            onIndividualClick
        ),
        Triple(
            "Групповое - физлица",
            "действует до 30 суток\nдо 5 человек на один билет",
            onGroupClick
        ),
        Triple(
            "Групповое - юрлица",
            "действует до 30 суток\nдо 50 человек на один билет",
            onCompanyClick
        ),
    )

    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Выберите необходимый\nтип разрешения",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(32.dp))
        buttons.forEach {
            ButtonWithHint(
                label = it.first,
                hint = it.second,
                onClick = it.third,
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .padding(bottom = 16.dp)
            )
        }
    }
}


@Composable
@Preview
private fun PermitTypeScreenPreview() {


    GreenKamchatkaTheme {
        PermitTypeScreen({}, {}, {})
    }
}