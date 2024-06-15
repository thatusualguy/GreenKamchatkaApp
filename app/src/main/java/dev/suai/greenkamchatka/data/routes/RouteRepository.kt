package dev.suai.greenkamchatka.data.routes

import dev.suai.greenkamchatka.data.GpsRoute

interface RouteRepository {
    suspend fun getRoutes(): List<Route>
    suspend fun getRoutes(zoneId: Int): List<Route>
    suspend fun getRouteGps(routeId: Int): GpsRoute
}