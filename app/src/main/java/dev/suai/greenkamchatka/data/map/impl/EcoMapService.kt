package dev.suai.greenkamchatka.data.map.impl

import dev.suai.greenkamchatka.data.routes.impl.RouteApi
import retrofit2.Call
import retrofit2.http.GET
interface EcoMapService {
    @GET("api/mapp/get_all_reports")
    fun getReports(): Call<EcoMapApi>
}