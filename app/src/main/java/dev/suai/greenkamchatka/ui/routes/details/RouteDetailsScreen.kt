package dev.suai.greenkamchatka.ui.routes.details

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import dev.suai.greenkamchatka.R
import dev.suai.greenkamchatka.data.routes.Route
import dev.suai.greenkamchatka.ui.theme.GreenKamchatkaTheme



@Composable
fun RouteDetailsScreen(
    route: Route,
    onRegisterPressed: (Int) -> Unit,
    onBackPressed: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())

    ) {
        TextButton(onClick = { onBackPressed }) {
            Text(
                text = "Маршруты",
                color = Color.Green
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = route.name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
//                .fillMaxWidth()
                .height(200.dp)
                .horizontalScroll(rememberScrollState())
        ) {
            route.images.forEach{
                AsyncImage(
                    modifier = Modifier.fillMaxHeight(),
                    contentScale = ContentScale.FillHeight,
                    model = it,
                    contentDescription = null,
                    placeholder = painterResource(id = R.drawable.ic_launcher_background)
                )
                Spacer(modifier = Modifier.width(8.dp))

            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = route.description,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { onRegisterPressed(route.id) },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Подать заявку на посещение",
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWaterfallTrailScreen() {

    val route = Route(
        -1,
        -1,
        "Тропа Водопадов",
        duration = 48,
        description = """Тропа Водопадов в Кавказских горах России - живописный маршрут, пролегающий через густые леса и вдоль бурлящих рек. Этот 10 километровый путь ведет к серии впечатляющих водопадов, где каждый следующий более грандиозен, чем предыдущий. По пути можно увидеть разнообразие флоры и фауны региона, а также насладиться захватывающими видами горных вершин и долин. Маршрут идеально подходит для любителей природы и фотографов, стремящихся запечатлеть красоту дикой природы Кавказа.""",
    )

    GreenKamchatkaTheme {
        RouteDetailsScreen(
            route, {}, {}
        )
    }
}