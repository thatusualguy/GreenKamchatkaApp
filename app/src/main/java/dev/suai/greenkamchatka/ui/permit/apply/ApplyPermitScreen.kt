package dev.suai.greenkamchatka.ui.permit.apply

import android.text.Layout
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.suai.greenkamchatka.R
import dev.suai.greenkamchatka.data.visitors.Visitor
import dev.suai.greenkamchatka.ui.components.ButtonWithHint
import dev.suai.greenkamchatka.ui.components.ListItem
import dev.suai.greenkamchatka.ui.components.MyDialog
import dev.suai.greenkamchatka.ui.components.MyTextField
import java.time.Instant
import java.util.Calendar
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplyPermitScreen(
    applyPermitState: ApplyPermitState,

    content: @Composable () -> Unit,
    onApplyForPermit: () -> Unit,
    onSavedSelect: (Int?) -> Unit,
    onDateChange: (Long) -> Unit,
    onReasonChange: (String) -> Unit
) {
    val spacerHeight = 10.dp

    val context = LocalContext.current
    val dateFormat = DateFormat.getMediumDateFormat(context)
    val calendar: Calendar = Calendar.getInstance()
    calendar.time = Date.from(Instant.ofEpochMilli(applyPermitState.date.millis))



    var showDateDialog by remember { mutableStateOf(false) }
    var showReasonDialog by remember { mutableStateOf(false) }
    var showVisitorDialog by remember { mutableStateOf(false) }

    if (showDateDialog) {
        DateDialog({ showDateDialog = false }, onDateChange)
    }

    if (showReasonDialog) {
        ReasonSelectDialog({ showReasonDialog = false }, applyPermitState.reason, onReasonChange)
    }

    if (showVisitorDialog) {
        VisitorDialog({ showVisitorDialog = false }, applyPermitState.savedVisitors, onSavedSelect)
    }


    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Column(
            Modifier
                .fillMaxWidth(0.8f)
                .verticalScroll(rememberScrollState())
        ) {

            Text(
                text = "Заполните данные для получения разрешения",
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(spacerHeight))

            ButtonWithHint(
                onClick = {showVisitorDialog = true},
                label = "Список посетителей",
                icon = painterResource(id = R.drawable.list),
                hint = "Если у вас заполнена форма посетителя\nв личном кабинете, выберите посетителя"
            )

            if (applyPermitState.selectedVisitor != null) {
                ListItem(
                    modifier = Modifier.fillMaxWidth()
                       ,
                    text = applyPermitState.savedVisitors.find { it.id==applyPermitState.selectedVisitor }.let { buildString {
                        append(it?.lastName)
                        append(" ")
                        append(it?.firstName)
                        append(" ")
                        append(it?.middleName)
                    }},
                    onClick = { onSavedSelect(null) },
                    painter = painterResource(id = R.drawable.cross)
                )
            }

            Spacer(modifier = Modifier.height(spacerHeight * 2))


                Text(
                    text = "Выберите дату посещения",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Left
                )

                Spacer(modifier = Modifier.height(spacerHeight / 2))

                Row (verticalAlignment = Alignment.CenterVertically) {

                    Box(modifier = Modifier
                        .weight(1f)
                        .clickable { showDateDialog = true }) {
                        MyTextField(
                            value = dateFormat.format(calendar.time),
                            label = "",
                            readOnly = true,
                            onValueChange = { showDateDialog = true },
                            enabled = false,
                            onClick = { showDateDialog = true }
                        )
                    }

                    Spacer(modifier = Modifier.width(spacerHeight))

                    Button(onClick = { showReasonDialog = true }, modifier = Modifier.height(50.dp)) {
                        Text(text = "Цель посещения", modifier = Modifier.align(Alignment.CenterVertically))
                    }
                }






            if (applyPermitState.selectedVisitor == null) {
                content()
            }

            Spacer(modifier = Modifier.height(spacerHeight * 2))

            Button(onClick = onApplyForPermit, Modifier.fillMaxWidth(0.8f)) {
                Text(text = "Отправить")
            }
        }
    }


}

@Composable
fun VisitorDialog(
    showVisitorDialog: () -> Unit,
    savedVisitors: List<Visitor>,
    onSavedSelect: (Int) -> Unit
) {
    MyDialog(onDismiss = showVisitorDialog) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            savedVisitors.forEach {
                ListItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    text = buildString {
                        append(it.lastName)
                        append(" ")
                        append(it.firstName)
                        append(" ")
                        append(it.middleName)
                        append(", ")
                        append(it.phone)
                    },
                    onClick = {
                        showVisitorDialog()
                        onSavedSelect(it.id!!)
                    })
            }
        }
    }
}

@Composable
fun ReasonSelectDialog(
    showReasonDialog: () -> Unit,
    reason: String,
    onReasonChange: (String) -> Unit
) {
    val reasons = listOf(
        "Однодневная поездка/экскурсия",
        "Многодневный пешеходный туризм",
        "Лыжный туризм",
        "Спортивные мероприятия",
        "Научные исследования",
        "Видео/фотосъемка",
        "Альпинизм",
        "Другое"
    )

    MyDialog(onDismiss = showReasonDialog) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            reasons.forEach {
                ListItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    text = it,
                    onClick = {
                        showReasonDialog()
                        onReasonChange(it)
                    })
            }
        }
    }
}


@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun DateDialog(showDateDialog: () -> Unit, onDateChange: (Long) -> Unit) {
    val datePickerState = rememberDatePickerState()
    DatePickerDialog(
        onDismissRequest = {
            showDateDialog()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    showDateDialog()

                    if (datePickerState.selectedDateMillis != null) {
                        onDateChange(datePickerState.selectedDateMillis!!)
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