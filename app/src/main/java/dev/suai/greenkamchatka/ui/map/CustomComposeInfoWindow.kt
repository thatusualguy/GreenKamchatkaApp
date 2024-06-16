package dev.suai.greenkamchatka.ui.map

import android.widget.FrameLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.infowindow.InfoWindow

class CustomComposeInfoWindow(mapView: MapView) :
    InfoWindow(FrameLayout(mapView.context), mapView) {

    init {
        val composeView = ComposeView(mapView.context)
        (mView as FrameLayout).addView(composeView)
    }

    override fun onOpen(item: Any?) {
        val marker = item as Marker
        val composeView = mView.findViewById<ComposeView>(0)
        composeView.setContent {
            CustomInfoWindow(title = marker.title, description = marker.snippet)
        }
    }

    override fun onClose() {
        // Do something when the InfoWindow is closed, if needed
    }
}


@Composable
@Preview
fun CustomInfoWindow(title: String = "", description: String = "") {

}