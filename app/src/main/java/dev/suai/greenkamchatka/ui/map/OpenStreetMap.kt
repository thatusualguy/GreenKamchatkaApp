package dev.suai.greenkamchatka.ui.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import dev.suai.greenkamchatka.data.GpsPoint
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay


@Preview
@Composable
fun OpenStreetMap(
    startPoint: GpsPoint? = null,
    startZoom: Double = 8.0,
    onUpdate: (MapView) -> Unit = {}
) {
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            MapView(context).apply {
                // set map source
                setTileSource(TileSourceFactory.OpenTopo)

                // add rotation
                val rotationGestureOverlay = RotationGestureOverlay(this)
                rotationGestureOverlay.isEnabled
                this.setMultiTouchControls(true)
                this.overlays.add(rotationGestureOverlay)

                // start point
                val mapController = this.controller
                mapController.setZoom(8.0)
                mapController.setCenter(GeoPoint(55.0, 160.0));
            }
        },
        update = { mapView ->
            onUpdate(mapView)
        }
    )
}