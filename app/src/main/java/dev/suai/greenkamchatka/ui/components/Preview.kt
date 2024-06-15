package dev.suai.greenkamchatka.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import dev.suai.greenkamchatka.ui.theme.Black
import dev.suai.greenkamchatka.ui.theme.GreenKamchatkaTheme

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, group = "buttons")
@Preview(showBackground = true, group = "buttons")
@Composable
fun DefaultButtonPreview() {
    GreenKamchatkaTheme {
        Column {
            Button(onClick = { }) {
                Text(text = "Default")
            }

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE7FFF0), contentColor = Black)
            ) {
                Text(text = "Default")
            }

            TextButton(onClick = { }) {
                Text(text = "Default")
            }

            ElevatedButton(onClick = { }) {
                Text(text = "Default")
            }

            OutlinedButton(onClick = {}) {
                Text(text = "Default")
            }

            ListItem(text = "Default")
        }
    }
}


@Composable
@Preview(showBackground = true)
fun DefaultCheckboxPreview() {
    GreenKamchatkaTheme {
        Column {
            repeat(2) {
                Checkbox(checked = false, onCheckedChange = {}, colors = CheckboxDefaults.colors())
                Checkbox(checked = true, onCheckedChange = {})
            }
        }
    }
}

@Composable
@Preview
fun MyCheckboxPreview2() {
    GreenKamchatkaTheme {
        Column {
            repeat(5) {
                MyCheckbox(checked = it % 2 == 0, label = "Чекбокс ыыыыы", onCheckedChange = null)
            }
        }
    }
}