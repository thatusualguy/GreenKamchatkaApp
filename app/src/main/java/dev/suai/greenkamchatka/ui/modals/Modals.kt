package dev.suai.greenkamchatka.ui.modals

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import dev.suai.greenkamchatka.ui.components.MyDialog
import dev.suai.greenkamchatka.ui.components.MyTextDialog
import dev.suai.greenkamchatka.ui.theme.GreenKamchatkaTheme


@Composable
fun DialogWithTitleAndMessage(onDismiss: () -> Unit = {}, title: String, message: String) {
    MyDialog(onDismiss = onDismiss) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = message,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        }
    }
}


@Composable
@Preview
fun DialogWaitPermit(onDismiss: () -> Unit = {}) {
    GreenKamchatkaTheme {
        DialogWithTitleAndMessage(
            onDismiss = onDismiss,
            title = "Данные успешно отправлены",
            message = "Ожидайте, вскоре вам на почту придет ответ на отправленную заявку"
        )
    }
}

@Composable
@Preview
fun DialogWaitReport(onDismiss: () -> Unit = {}) {
    GreenKamchatkaTheme {
        DialogWithTitleAndMessage(
            onDismiss = onDismiss,
            title = "Обращение успешно создано",
            message = "При следующем подключении к интернету обращение будет передано ответственным службам"
        )
    }
}

@Composable
@Preview
fun DialogSentReport(onDismiss: () -> Unit = {}) {
    GreenKamchatkaTheme {
        DialogWithTitleAndMessage(
            onDismiss = onDismiss,
            title = "Обращение успешно отправлено",
            message = "Обращение передано ответственным службам"
        )
    }
}

@Composable
@Preview
fun DialogVisitorCreated(onDismiss: () -> Unit = {}) {
    GreenKamchatkaTheme {
        MyTextDialog(onDismiss = onDismiss, message = "Пользователь успешно добавлен")
    }
}

