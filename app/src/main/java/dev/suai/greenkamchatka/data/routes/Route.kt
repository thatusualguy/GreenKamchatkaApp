package dev.suai.greenkamchatka.data.routes

import dev.suai.greenkamchatka.data.GpsRoute

data class Route(
    val id: Int = -1,
    val zoneId: Int =-1,
    val name: String = "",
    val duration:Int = -1,
    val route: GpsRoute? = null,
    val description: String = "",
    val images: List<String> = emptyList(),
//    val tags: List<String>
)

data class RouteMinified(
    val id: Int,
    val name: String,
    val route: GpsRoute?
) {
    fun fromRoute(route: Route) {
        RouteMinified(route.id, route.name, route.route)
    }
}