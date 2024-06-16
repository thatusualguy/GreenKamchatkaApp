package dev.suai.greenkamchatka.ui.ecomap

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import dev.suai.greenkamchatka.R
import dev.suai.greenkamchatka.data.map.ReportMarker
import dev.suai.greenkamchatka.ui.map.OpenStreetMapWithReports

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

fun EcoMapScreen(reports: List<ReportMarker>, sendReport: () -> Unit) {

    Scaffold(
        floatingActionButton = {

            ExtendedFloatingActionButton(
                text = { Text("Отправить\nобращение") },
                icon = { Icon(painter = painterResource(id = R.drawable.plus), contentDescription = "") },
                onClick = {
                    sendReport.invoke()
                }
            )
        }
    ) {
        OpenStreetMapWithReports(reports = reports)
    }
}
