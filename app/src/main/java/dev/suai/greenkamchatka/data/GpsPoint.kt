package dev.suai.greenkamchatka.data

data class GpsPoint(
    val lat: Double,
    val lon: Double
)

data class GpsRoute(
    val route: List<GpsPoint>
)

data class GpsZone(
    val zone: List<GpsPoint>
)