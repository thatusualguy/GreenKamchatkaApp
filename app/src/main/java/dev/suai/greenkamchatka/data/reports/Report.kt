package dev.suai.greenkamchatka.data.reports

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import dev.suai.greenkamchatka.data.GpsPoint
import java.io.File

@Entity
data class Report(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val type: Int = 1,
    val imageUri: Uri? = null,
    val email: String = "",
    val phone: String = "",
    val comment: String = "",
    val time: Long = 0,
    val location: GpsPoint = GpsPoint(0.0, 0.0)
)


class Converters {

    @TypeConverter
    fun fromUri(uri: Uri?): String? {
        return uri?.toString()
    }

    @TypeConverter
    fun toUri(uriString: String?): Uri? {
        return Uri.fromFile(File(uriString))
//        return uriString?.let { Uri.parse(it) }
    }

    @TypeConverter
    fun fromGpsPoint(point: GpsPoint): String {
        return point.lat.toString() + " " + point.lon.toString()
    }

    @TypeConverter
    fun toGpsPoint(pointString: String?): GpsPoint {
        return GpsPoint(
            pointString?.split(" ")?.map { it.toDouble() }?.get(0) ?: 0.0,
            pointString?.split(" ")?.map { it.toDouble() }?.get(1) ?: 0.0
        )

    }
}