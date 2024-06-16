package dev.suai.greenkamchatka.ui.report.send

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.suai.greenkamchatka.ui.components.DropDownMenu
import dev.suai.greenkamchatka.ui.components.MyTextField
import dev.suai.greenkamchatka.ui.components.SelectImageFromGallery
import dev.suai.greenkamchatka.ui.theme.GreenKamchatkaTheme

@Composable
fun FileReportScreen(
    state: FileReportState,
    viewUnsentReports: () -> Unit,
    sendReport: () -> Unit,
    onBackPressed: () -> Unit,
    onTypeChange: (Int) -> Unit,
    onEmailChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit,
    onCommentChange: (String) -> Unit,
    onImageChanged: (Uri?) -> Unit,
) {

    val spacerHeight = 10.dp

    val types = listOf(
        Pair(1, "Мусор"),
        Pair(2, "Кострища"),
        Pair(3, "Браконьерство"),
        Pair(4, "Пожары"),
        Pair(5, "Другое"),
    )


    Column(Modifier.fillMaxSize()) {
        Column(
            Modifier
//                .verticalScroll(rememberScrollState())
                .fillMaxWidth(0.8f)
                .align(Alignment.CenterHorizontally)
        ) {

            Text(
                text = "Обращение",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(spacerHeight))
            SelectImageFromGallery(state.imageUri, onImageChanged)
            Spacer(modifier = Modifier.height(spacerHeight))

            Text(text = "Тип события")
            Spacer(modifier = Modifier.height(spacerHeight / 2))
            DropDownMenu(
                label = "",
                items = types.map { it.second },
                selected = state.type-1,
                onSelectionChanged = { onTypeChange(types[it].first) })

            Spacer(modifier = Modifier.height(spacerHeight))

            Text(text = "Контактные данные")
            Spacer(modifier = Modifier.height(spacerHeight / 2))
            MyTextField(value = state.email, onValueChange = onEmailChange, label = "Email")
            Spacer(modifier = Modifier.height(spacerHeight / 2))
            MyTextField(value = state.phone, onValueChange = onPhoneChange, label = "Телефон")

            Spacer(modifier = Modifier.height(spacerHeight))
            Text(text = "Комментарий")
            Spacer(modifier = Modifier.height(spacerHeight / 2))

            MyTextField(
                value = state.comment,
                onValueChange = onCommentChange,
                label = "",
                singleLine = false,
                modifier = Modifier.weight(1f),
                lines = 6
            )
            Spacer(modifier = Modifier.height(spacerHeight))

            Button(onClick = sendReport, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(text = "Отправить обращение")
            }
        }
    }
}


@Preview
@Composable
fun FileReportScreenPreview() {
    val state = FileReportState(1, null, "", "", "")

    GreenKamchatkaTheme {
        FileReportScreen(
            state,
            {},
            {},
            {},
            {},
            {},
            {},
            {},
            {}
        )
    }
}