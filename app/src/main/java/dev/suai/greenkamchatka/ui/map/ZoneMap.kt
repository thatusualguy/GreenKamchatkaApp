package dev.suai.greenkamchatka.ui.map

import android.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.suai.greenkamchatka.data.GpsPoint
import dev.suai.greenkamchatka.data.GpsZone
import dev.suai.greenkamchatka.data.zones.Zone
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.Polygon
import org.osmdroid.views.overlay.gridlines.LatLonGridlineOverlay.backgroundColor


@Composable
fun OpenStreetMapWithZones(
    startPoint: GpsPoint? = null,
    startZoom: Double = 8.0,
    zones: List<Zone>,
    onZoneTap: (Int) -> Unit = {}
) {
    OpenStreetMap(
        startPoint,
        startZoom
    )
    {
        it.overlays.clear()

        for (zone in zones) {
            val geoPoints =
                zone.border.zone.map { gpsPoint ->
                    GeoPoint(gpsPoint.lat, gpsPoint.lon)
                }.toList()

            val zonePolygon = Polygon();
            zonePolygon.setPoints(geoPoints);

            zonePolygon.id = zone.id.toString()


            // TODO set colors and border
            zonePolygon.fillPaint.color = Color.parseColor("#1000FF00")
            zonePolygon.outlinePaint.strokeWidth = 3f
            zonePolygon.outlinePaint.color = Color.parseColor("#FF000000")

            zonePolygon.setOnClickListener { polygon, _, _ ->
                onZoneTap(polygon.id.toInt())
                true
            }

            // show zone name
            val nameMarker = Marker(it)
            nameMarker.textLabelBackgroundColor = Color.TRANSPARENT
            nameMarker.textLabelForegroundColor = Color.BLACK
            nameMarker.setTextIcon(zone.name)
            nameMarker.setPosition(zonePolygon.infoWindowLocation)



            it.overlays.add(nameMarker)
            it.overlays.add(zonePolygon)
        }
        it.invalidate()
    }
}

@Preview
@Composable
fun OpenStreetMapWithZonesPreview() {
    val zone = Zone(
        1, "Тест", "", GpsZone(
            listOf(
                GpsPoint(55.0, 160.0),
                GpsPoint(55.0, 161.0),
                GpsPoint(56.0, 161.0),
                GpsPoint(56.0, 160.0),
                GpsPoint(55.0, 160.0),
            )
        )
    )

    OpenStreetMapWithZones(zones = listOf(zone))
}
