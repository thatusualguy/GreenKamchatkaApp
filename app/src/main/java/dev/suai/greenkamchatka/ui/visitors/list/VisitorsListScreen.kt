package dev.suai.greenkamchatka.ui.visitors.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.suai.greenkamchatka.R
import dev.suai.greenkamchatka.ui.components.ListItem
import dev.suai.greenkamchatka.ui.theme.GreenKamchatkaTheme

@Composable
fun VisitorsListScreen(
    visitors: List<Pair<Int, String>>,
    onItemClick: (Int) -> Unit,
    onItemDelete: (Int) -> Unit,
    onItemCreate: () -> Unit
) {

    val iconSize = 16.dp

    Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
        Column(
            Modifier
                .fillMaxWidth(0.85f)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Список посетителей", style = MaterialTheme.typography.headlineLarge)

            Spacer(modifier = Modifier.height(16.dp))

            visitors.forEach {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(
                        modifier = Modifier
                            .width(iconSize)
                    )
                    ListItem(
                        modifier = Modifier
                            .weight(1f)
                            .height(60.dp),
                        text = it.second,
                        onClick = { onItemClick(it.first) })
                    Icon(
                        painter = painterResource(id = R.drawable.delete),
                        contentDescription = null,
                        modifier = Modifier
                            .size(iconSize)
                            .clickable { onItemDelete(it.first) },
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {

                Spacer(
                    modifier = Modifier
                        .width(iconSize)
                )
                ListItem(
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp),
                    text = "Добавить посетителя",
                    onClick = onItemCreate,
                    painter  = painterResource(id =R.drawable.plus)
                )
                Spacer(
                    modifier = Modifier
                        .width(iconSize)
                )
            }
        }
    }
}


@Composable
@Preview
private fun VisitorsListScreenPreview() {
    val visitors = listOf(
        Pair(1, "Савина Анастасия Дмитриевна"),
        Pair(2, "Савина Анастасия Дмитриевна"),
        Pair(3, "Савина Анастасия Дмитри")
    )

    GreenKamchatkaTheme {
        VisitorsListScreen(visitors, {}, {}, {})
    }
}