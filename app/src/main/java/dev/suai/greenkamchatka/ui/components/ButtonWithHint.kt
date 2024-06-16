package dev.suai.greenkamchatka.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.List
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.suai.greenkamchatka.R
import dev.suai.greenkamchatka.ui.theme.GreenKamchatkaTheme

@Composable
fun ButtonWithHint(
    label: String,
    modifier: Modifier = Modifier,
    icon: Painter? = null,
    hint: String,
    onClick: () -> Unit = {}
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = label)

                if (icon != null)
                    Icon(
                        modifier = Modifier
                            .size(36.dp)
                            .padding(start = 4.dp),
                        painter = icon,
                        contentDescription = null
                    )
            }
        }
        Text(
            text = hint,
            modifier = Modifier
                .alpha(0.4f)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}


@Composable
@Preview
fun ButtonWithHintPreview() {

    GreenKamchatkaTheme {
        ButtonWithHint(
            label = "Список посетителей",
            icon = painterResource(id = R.drawable.list),
            hint = "Если у вас заполнена форма посетителя\nв личном кабинете, выберите посетителя"
        )
    }
}