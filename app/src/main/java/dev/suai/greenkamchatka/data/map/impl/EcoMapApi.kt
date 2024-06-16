package dev.suai.greenkamchatka.data.map.impl

data class EcoMapApi(val reports: List<EcoMapMarkerApi>)

data class EcoMapMarkerApi(
    val status_id: Int,
    val status_name: String,
    val type_of_report: String,
    val type_id: Int,
    val coords: List<Float>
)