package dev.suai.greenkamchatka.data.routes.impl

import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Streaming


interface RouteService {
    @GET("api/mapp/routes_all")
    fun getRoutes(): Call<RouteApi>

    @GET("static/appdata/{zone_id}/{route_id}/route.gpx")
    @Streaming
    fun getRouteGpx(
        @Path("zone_id") zoneId: Int,
        @Path("route_id") routeId: Int
    ): Call<ResponseBody>
}