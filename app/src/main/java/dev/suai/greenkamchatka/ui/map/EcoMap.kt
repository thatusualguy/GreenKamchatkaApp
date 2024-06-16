package dev.suai.greenkamchatka.ui.map

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import dev.suai.greenkamchatka.R
import dev.suai.greenkamchatka.data.GpsPoint
import dev.suai.greenkamchatka.data.map.ReportMarker
import dev.suai.greenkamchatka.ui.theme.DarkGreen
import dev.suai.greenkamchatka.ui.theme.Gray
import dev.suai.greenkamchatka.ui.theme.Green
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker


@Composable
fun OpenStreetMapWithReports(
    startPoint: GpsPoint? = null,
    startZoom: Double = 8.0,
    reports: List<ReportMarker>
) {

    val colors =
        listOf(
            Green.toArgb(),
            Gray.toArgb(),
            DarkGreen.toArgb()
        )


    val context = LocalContext.current

    OpenStreetMap(
        startPoint, startZoom
    )
    {

        for (report in reports) {

            val geoPoint = GeoPoint(report.point.lat, report.point.lon)

            val iconMarker = Marker(it)
            iconMarker.setPosition(geoPoint)
            val drawable = when(report.typeId) {
                1 -> context.getDrawable(R.drawable.trash) // мусор
                2 -> context.getDrawable(R.drawable.fireplace) // кострища
                3 -> context.getDrawable(R.drawable.squirrel) // браконьерство
                4 -> context.getDrawable(R.drawable.fire) // пожары
                5 -> context.getDrawable(R.drawable.other) // другое
                else -> {context.getDrawable(R.drawable.other)}
            }
            iconMarker.title = report.type
            iconMarker.subDescription = "desc"

            iconMarker.infoWindow

            drawable?.setTint(
                when(report.statusId){
                    1-> colors[0]
                    2 -> colors[1]
                    3 -> colors[2]
                    else -> colors[2]
                }
            )

            iconMarker.icon = drawable


//            iconMarker.setOnMarkerClickListener { marker, _ ->
////                marker.closeInfoWindow()
//                false
//            }

            it.overlays.add(iconMarker)


//            val textMarker = Marker(it)
//            textMarker.setPosition(geoPoint)
//            textMarker.textLabelBackgroundColor = Color.TRANSPARENT
//            textMarker.textLabelForegroundColor = Color.BLACK
//            textMarker.setTextIcon(report.type + "\n(${report.status})")
//
//            textMarker.setOnMarkerClickListener { marker, _ ->
//                marker.closeInfoWindow()
//                false
//            }
//
//            it.overlays.add(textMarker)
        }
        it.invalidate()
    }
}


