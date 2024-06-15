package dev.suai.greenkamchatka.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.suai.greenkamchatka.ui.theme.GreenKamchatkaTheme

@Composable
fun MyTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String? = null,
    singleLine: Boolean = true,
    isError: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    onImeDone: (() -> Unit)? = null,
    readOnly: Boolean = false,
    enabled: Boolean = true,
    onClick: ()->Unit = {}
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth().height(60.dp).clickable { onClick() },

        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        placeholder = { if (placeholder != null) Text(text = placeholder) },
        singleLine = singleLine,
        readOnly = readOnly,
        isError = isError,
        enabled = enabled,
        keyboardActions = KeyboardActions(
            onSend = { onImeDone?.invoke() }
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = if (onImeDone == null) ImeAction.Next else ImeAction.Send
        ),
        colors = OutlinedTextFieldDefaults.colors(
            
            unfocusedBorderColor = Color.Transparent,
            unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
            unfocusedLabelColor = Color.DarkGray,
            unfocusedTextColor = Color.DarkGray,
//            unfocusedLabelColor = MaterialTheme.colorScheme.primary
        ),
        shape = MaterialTheme.shapes.medium
    )
}


@Composable
@Preview
fun MyTextFieldPreview() {
    GreenKamchatkaTheme {
        Box(modifier = Modifier.padding(32.dp)) {
            MyTextField(
                value = "",
                onValueChange = {},
                label = "Фамилия",
                placeholder = "Введите фамилию",
                singleLine = true,
                isError = false
            )
        }
    }
}























