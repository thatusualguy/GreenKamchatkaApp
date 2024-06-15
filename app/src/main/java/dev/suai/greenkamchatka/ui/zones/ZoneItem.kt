package dev.suai.greenkamchatka.ui.zones

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.suai.greenkamchatka.R
import dev.suai.greenkamchatka.data.GpsZone
import dev.suai.greenkamchatka.data.zones.Zone
import dev.suai.greenkamchatka.ui.theme.GreenKamchatkaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZoneItem(
    zone: Zone,
    onClick: (Int) -> Unit = {}
) {
    Card(
        onClick = { onClick(zone.id) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row() {
            Text(text = zone.name)
            AsyncImage(
                modifier = Modifier.size(100.dp, 100.dp),
                model = zone.imageUrl,
                contentDescription = zone.name,
                placeholder = painterResource(R.drawable.ic_launcher_background),
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ZoneItemPreview() {

    val testZone = Zone(
        id = 1,
        name = "Налычево",
        imageUrl = "https://farm4.staticflickr.com/3075/3168662394_7d7103de7d_z_d.jpg",
        border = GpsZone(
            emptyList()
        )
    )

    GreenKamchatkaTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            repeat(5) {
                ZoneItem(zone = testZone)
            }
        }
    }
}