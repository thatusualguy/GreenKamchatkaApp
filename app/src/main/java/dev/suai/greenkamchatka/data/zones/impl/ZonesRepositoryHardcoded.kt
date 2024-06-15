package dev.suai.greenkamchatka.data.zones.impl

import android.content.Context
import dev.suai.greenkamchatka.R
import dev.suai.greenkamchatka.data.GpsPoint
import dev.suai.greenkamchatka.data.GpsZone
import dev.suai.greenkamchatka.data.zones.Zone
import dev.suai.greenkamchatka.data.zones.ZonesRepository
import io.ticofab.androidgpxparser.parser.GPXParser
import io.ticofab.androidgpxparser.parser.domain.Gpx
import me.bvn13.sdk.android.gpx.GpxReader
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException
import java.io.InputStream


class ZonesRepositoryHardcoded(private val context: Context) : ZonesRepository {

    private var zones: List<Zone>? = null


    private val zonesNames = listOf("Парк Налычево", "ЮКП (север)", "о. Крашенинникова", "Река Коль заказник", "ПП Ключевской", "ЮКП (юг)", "граница БПП", "ПП Вилючинский")
//    private val zonesNames = listOf("Парк Налычево")


    fun loadZones() {
        val inputStream: InputStream = context.resources.openRawResource(R.raw.borders)


        val parser = GPXParser() // consider injection
        try {
            val parsedGpx: Gpx? = parser.parse(inputStream) // consider using a background thread
            parsedGpx?.let {
                val gpsZones = mutableListOf<GpsZone>()
                for (park in it.tracks.orEmpty()) {
                    val pts = park.trackSegments?.getOrNull(0)?.trackPoints.orEmpty()
                    gpsZones.add(GpsZone(pts.map { GpsPoint(it.latitude, it.longitude) }))
                }
                zones = gpsZones.mapIndexed { index, s -> Zone(index+1, zonesNames[index] ,"", s) }

            } ?: {
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        }
    }


    override suspend fun getZones(): List<Zone> {
        if (zones == null)
            loadZones()

        return zones!!
    }
}