package dev.suai.greenkamchatka.ui.components

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.ExposedDropdownMenuDefaults.TrailingIcon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import dev.suai.greenkamchatka.ui.theme.GreenKamchatkaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenu(
    modifier: Modifier = Modifier,
    label: String,
    items: List<String>,
    selected: Int,
    onSelectionChanged: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(modifier = modifier,
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        OutlinedTextField(
            modifier = modifier.menuAnchor(),
            readOnly = true,
            value = items[selected],
            onValueChange = { },
            label = {
                Text(label)
            },
            shape = MaterialTheme.shapes.medium,
            trailingIcon = {
                TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.Transparent,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
                unfocusedLabelColor = Color.DarkGray,
                unfocusedTextColor = Color.DarkGray,
            )
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            items.forEachIndexed { idx, option ->
                DropdownMenuItem(
                    onClick = {
                        onSelectionChanged(idx)
                        expanded = false
                    },
                    text = {
                        Text(text = option)
                    }
                )
            }
        }
    }
}

@Composable
@Preview
fun DropDownMenuPreview() {
    GreenKamchatkaTheme {
        var selected by remember {
            mutableIntStateOf(0)
        }

        DropDownMenu(modifier = Modifier, "Label", listOf("1", "2"), selected) { selected = it }
    }
}