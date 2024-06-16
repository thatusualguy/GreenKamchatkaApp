package dev.suai.greenkamchatka.ui.zones

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import dev.suai.greenkamchatka.R
import dev.suai.greenkamchatka.data.zones.Zone
import dev.suai.greenkamchatka.ui.components.ListItem
import dev.suai.greenkamchatka.ui.map.OpenStreetMapWithZones
import dev.suai.greenkamchatka.ui.theme.GreenKamchatkaTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZonesScreen(
    zones: List<Zone> = emptyList(),
    onBackPress:()->Unit = {},
    onZoneSelection: (Int) -> Unit = {},
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()



    Scaffold(
        floatingActionButton = {
            if (!showBottomSheet)
                ExtendedFloatingActionButton(
                    text = { Text("Показать списком") },
                    icon = { Icon(painter = painterResource(id = R.drawable.plus), contentDescription = "") },
                    onClick = {
                        showBottomSheet = true
                    }
                )
        }
    ) {


        OpenStreetMapWithZones(zones = zones, onZoneTap = onZoneSelection )

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState,
                containerColor = MaterialTheme.colorScheme.background
            ) {

                LazyColumn(state = rememberLazyListState(), modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    items(zones) {
                        ListItem(modifier = Modifier.fillMaxWidth(0.8f), text=it.name, onClick = {onZoneSelection(it.id)})

//                        ZoneItem(zone = it, onClick = onZoneSelection)
                    }
                }
            }
        }

    }
}

@Composable
@Preview
fun ZonesScreenPreview() {
    GreenKamchatkaTheme {
        ZonesScreen(zones = emptyList(), onZoneSelection = {})
    }
}