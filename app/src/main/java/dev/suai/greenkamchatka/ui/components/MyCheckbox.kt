package dev.suai.greenkamchatka.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.suai.greenkamchatka.ui.theme.GreenKamchatkaTheme

@Composable
fun MyCheckbox(
    checked: Boolean,
    modifier: Modifier = Modifier,
    label: String? = null,
    onCheckedChange: ((Boolean) -> Unit)?
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {

        Checkbox(
            checked = checked, onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colorScheme.secondary,
                checkmarkColor = MaterialTheme.colorScheme.primary,
                uncheckedColor = MaterialTheme.colorScheme.secondary,
            )
        )
        if (label != null)
            Text(text = label, style = MaterialTheme.typography.bodyLarge)
    }
}


@Composable
@Preview
fun MyCheckboxPreview() {
    GreenKamchatkaTheme {
        Column {
            repeat(5) {
                MyCheckbox(checked = it % 2 == 0, label = "Чекбокс ыыыыы", onCheckedChange = null)
            }
        }
    }
}