package dev.suai.greenkamchatka.data.zones

import dev.suai.greenkamchatka.data.GpsPoint
import dev.suai.greenkamchatka.data.GpsZone

data class Zone(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val border: GpsZone
)

