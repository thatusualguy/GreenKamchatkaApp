package dev.suai.greenkamchatka.ui.visitors.add

import android.text.format.DateFormat
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.suai.greenkamchatka.data.visitors.Gender
import dev.suai.greenkamchatka.data.visitors.Visitor
import dev.suai.greenkamchatka.ui.components.DropDownMenu
import dev.suai.greenkamchatka.ui.components.MyTextField
import dev.suai.greenkamchatka.ui.theme.GreenKamchatkaTheme
import java.time.Instant
import java.util.Calendar


private typealias StrCallback = (String) -> Unit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddVisitorScreen(
    visitor: Visitor,
    onBackPress: () -> Unit,
    onSavePress: () -> Unit,
    onFirstNameChange: (String) -> Unit,
    onMiddleNameChange: StrCallback,
    onLastNameChange: StrCallback,
    onEmailChange: StrCallback,
    onPhoneChange: StrCallback,
    onDobChange: (Long) -> Unit,
    onGenderChange: (Gender) -> Unit,
    onCitizenshipChange: StrCallback,
    onRegRegionChange: StrCallback,
    onPassportSeriesChange: StrCallback,
    onPassportNumChange: StrCallback
) {
    val spacerHeight = 10.dp


    val context = LocalContext.current
    val dateFormat = DateFormat.getMediumDateFormat(context)

    var showDialog by remember { mutableStateOf(false) }

    val regions = listOf("Санкт-Петербург", "Камчатка", "Москва")
    val countries = listOf("Санкт-Петербург", "Камчатка", "Москва")

    if (showDialog) {
        val datePickerState = rememberDatePickerState()


        DatePickerDialog(
            onDismissRequest = {
                showDialog = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDialog = false

                        if (datePickerState.selectedDateMillis != null) {
                            val calendar: Calendar = Calendar.getInstance()
                            calendar.setTimeInMillis(datePickerState.selectedDateMillis!!)
                            onDobChange(calendar.time.toInstant().epochSecond)
                        }
                    }
                ) {
                    Text("Выбрать")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }


    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Добавить пользователя", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(spacerHeight))

        MyTextField(
            value = visitor.lastName,
            onValueChange = onLastNameChange,
            label = "Фамилия",
            placeholder = "Иванов"
        )

        Spacer(modifier = Modifier.height(spacerHeight))

        MyTextField(
            value = visitor.firstName,
            onValueChange = onFirstNameChange,
            label = "Имя",
            placeholder = "Иван"
        )
        Spacer(modifier = Modifier.height(spacerHeight))

        MyTextField(
            value = visitor.middleName,
            onValueChange = onMiddleNameChange,
            label = "Отчество",
            placeholder = "Иванович"
        )
        Spacer(modifier = Modifier.height(spacerHeight))


        MyTextField(
            value = visitor.email,
            onValueChange = onEmailChange,
            label = "Эл. почта",
            placeholder = "ivan@example.com"
        )
        Spacer(modifier = Modifier.height(spacerHeight))
        MyTextField(
            value = visitor.phone,
            onValueChange = onPhoneChange,
            label = "Телефон",
            placeholder = "+7..."
        )
        Spacer(modifier = Modifier.height(spacerHeight * 2))

        Text(
            text = "Дата рождения",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Left
        )

        Spacer(modifier = Modifier.height(spacerHeight / 2))

        Box(modifier = Modifier.clickable { showDialog = true }) {
            MyTextField(
                value = dateFormat.format(visitor.dob),
                label = "",
                readOnly = true,
                onValueChange = { showDialog = true },
                enabled = false,
                onClick = {showDialog = true}
            )
        }

        Spacer(modifier = Modifier.height(spacerHeight * 2))

        Row {


            Column(Modifier.weight(0.5f)) {
                Text(
                    text = "Пол",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Left
                )
                Spacer(modifier = Modifier.height(spacerHeight / 2))
                DropDownMenu(
                    modifier = Modifier,
                    label = "",
                    items = listOf("М", "Ж"),
                    selected = if (visitor.gender == Gender.Male) 0 else 1
                ) {
                    onGenderChange(if (it == 0) Gender.Male else Gender.Female)
                }
            }
            Spacer(Modifier.width(16.dp))


            Column(Modifier.weight(1f)) {
                Text(
                    text = "Гражданство",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Left
                )
                Spacer(modifier = Modifier.height(spacerHeight / 2))
                DropDownMenu(
                    modifier = Modifier,
                    label = "",
                    items = countries,
                    selected = countries.indexOf(visitor.citizenship).coerceAtLeast(0)
                ) {
                    onRegRegionChange(regions[it])
                }
            }


        }
        Spacer(modifier = Modifier.height(spacerHeight * 2))

        Text(
            text = "Регион регистрации",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Left
        )
        Spacer(modifier = Modifier.height(spacerHeight / 2))
        DropDownMenu(
            modifier = Modifier.fillMaxWidth(),
            label = "",
            items = regions,
            selected = regions.indexOf(visitor.registrationRegion).coerceAtLeast(0)
        ) {
            onRegRegionChange(regions[it])
        }
        Spacer(modifier = Modifier.height(spacerHeight * 2))

        Text(
            text = "Паспорт",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Left
        )

        Spacer(modifier = Modifier.height(spacerHeight / 2))

        Row {


            Column(Modifier.weight(0.4f)) {
                MyTextField(
                    value = visitor.passportSeries,
                    onValueChange = onPassportSeriesChange,
                    label = "Серия"
                )
            }
            Spacer(Modifier.width(16.dp))

            Column(Modifier.weight(0.6f)) {

                MyTextField(
                    value = visitor.passportNum,
                    onValueChange = onPassportNumChange,
                    label = "Номер"
                )

            }
        }

        Button(onClick = onSavePress) {
            Text(text = "Сохранить")
        }
    }
}


@Composable
@Preview
fun AddVisitorScreenPreview() {
    GreenKamchatkaTheme {
        AddVisitorScreen(Visitor(
            0,
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            Gender.Male,
            Instant.now().epochSecond
        ), {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {})
    }
}