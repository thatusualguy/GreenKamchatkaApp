package dev.suai.greenkamchatka.ui.map

import android.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.suai.greenkamchatka.data.GpsPoint
import dev.suai.greenkamchatka.data.GpsRoute
import dev.suai.greenkamchatka.data.routes.Route
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.Polyline

@Composable
fun OpenStreetMapWithRoutes(
    startPoint: GpsPoint? = null,
    startZoom: Double = 8.0,
    routes: List<Route>,
    onRouteTap: (Int) -> Unit = {}
) {
    OpenStreetMap(
        startPoint, startZoom
    )
    {

        for (route in routes) {

            val geoPoints =
                route.route?.route?.map { gpsPoint ->
                    GeoPoint(gpsPoint.lat, gpsPoint.lon)
                }.orEmpty()

            val routePolyline = Polyline()
            routePolyline.setPoints(geoPoints)
            routePolyline.id = route.id.toString()
            routePolyline.setOnClickListener { polyline, _, _ ->
                onRouteTap(polyline.id.toInt())
                false
            }

            it.overlays.add(routePolyline);

            val nameMarker = Marker(it)
            nameMarker.id = route.id.toString()

            nameMarker.textLabelBackgroundColor = Color.TRANSPARENT
            nameMarker.textLabelForegroundColor = Color.BLACK
            nameMarker.setPosition(routePolyline.infoWindowLocation)

            nameMarker.setTextIcon(route.name)

            nameMarker.setOnMarkerClickListener { marker, _ ->
                onRouteTap(marker.id.trim().toInt())
                marker.closeInfoWindow()
                false
            }

            it.overlays.add(nameMarker)
        }
        it.invalidate()
    }
}


@Preview
@Composable
fun OpenStreetMapWithRoutesPreview() {
    val route = Route(
        1, 1, "Тест22", route = GpsRoute(
            listOf(
                GpsPoint(55.0, 160.0),
                GpsPoint(55.0, 161.0),
                GpsPoint(56.0, 161.0),
                GpsPoint(56.0, 160.0),
                GpsPoint(57.0, 164.0),
            )
        ),
        duration = 5,
        description = "",
        images = emptyList()
    )


    OpenStreetMapWithRoutes(routes = listOf(route))
}
