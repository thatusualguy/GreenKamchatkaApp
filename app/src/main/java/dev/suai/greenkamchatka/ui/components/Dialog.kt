package dev.suai.greenkamchatka.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import dev.suai.greenkamchatka.R
import dev.suai.greenkamchatka.ui.theme.GreenKamchatkaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDialog(
    onDismiss: () -> Unit = {},
    content: @Composable () -> Unit
) {

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties()
    )
    {
        ElevatedCard(
            onClick = { onDismiss() },
            modifier = Modifier.padding(0.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.background
            )
        ) {

            Box {
                Row(
                    Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ) {
                    TextButton(onClick = onDismiss, modifier = Modifier.size(40.dp)) {
                        Icon(painter = painterResource(id = R.drawable.cross), contentDescription = null)
                    }
                }

                Column(
                    modifier = Modifier
                        .padding(vertical = 48.dp, horizontal = 0.dp)
                        .fillMaxWidth()
                ) {
                    content.invoke()
                }
            }
        }
    }
}

@Composable
fun MyTextDialog(message: String, onDismiss: () -> Unit) {
    MyDialog(
        onDismiss = onDismiss,
        content = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    message,
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center
                )
            }
        }
    )
}


@Preview(showBackground = true, group = "dialog")
@Composable
private fun MyDialogPreview() {
    GreenKamchatkaTheme {
        MyDialog {
            Column {
                repeat(4) {
                    MyCheckbox(checked = it % 2 == 0, label = "Башкирочка") { }
                }
            }
        }
    }
}

@Preview(showBackground = true, group = "dialog")
@Composable
private fun MyTextDialogPreview() {
    GreenKamchatkaTheme {
        MyTextDialog(onDismiss = {}, message = "Обращение успешно отправлено")
    }
}