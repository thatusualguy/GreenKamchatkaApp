package dev.suai.greenkamchatka.ui.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.suai.greenkamchatka.ui.theme.GreenKamchatkaTheme

@Composable
fun MenuRoute(
    onZonesClick: () -> Unit = {},
    onEcomapClick: () -> Unit = {},
    onPersonsClick: () -> Unit = {},
    onReportClick: () -> Unit = {},
) {
    MenuScreen(onZonesClick, onEcomapClick, onPersonsClick, onReportClick)
}


@Composable
fun MenuScreen(
    onZonesClick: () -> Unit = {},
    onEcomapClick: () -> Unit = {},
    onPersonsClick: () -> Unit = {},
    onReportClick: () -> Unit = {},
) {
    val buttons = listOf(
        Pair(onZonesClick, "Маршруты"),
        Pair(onEcomapClick, "Экологическая обстановка"),
        Pair(onPersonsClick, "Список посетителей"),
        Pair(onReportClick, "Отправить обращение"),
    )

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Column(
            Modifier
                .fillMaxHeight()
                .width(IntrinsicSize.Max),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Меню", style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(16.dp))
            buttons.forEach {
                Button(onClick = {it.first.invoke()}, Modifier.fillMaxWidth()) {
                    Text(it.second, Modifier.padding(horizontal = 32.dp))
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}


@Composable
@Preview
private fun MenuScreenPreview() {
    GreenKamchatkaTheme {
        MenuScreen()
    }
}