package dev.suai.greenkamchatka.data.routes.impl

import android.app.Application
import android.util.Log
import dev.suai.greenkamchatka.TAG
import dev.suai.greenkamchatka.data.GpsPoint
import dev.suai.greenkamchatka.data.GpsRoute
import dev.suai.greenkamchatka.data.other.isInternetAvailable
import dev.suai.greenkamchatka.data.routes.Route
import dev.suai.greenkamchatka.data.routes.RouteRepository
import dev.suai.greenkamchatka.di.BASE_URL
import io.ticofab.androidgpxparser.parser.GPXParser
import io.ticofab.androidgpxparser.parser.domain.Gpx
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RouteRepositoryApi(private val app: Application) : RouteRepository {
    private val routes = mutableListOf<Route>()

    init {
        if (isInternetAvailable(app)) {
            getRoutesFromApi()
        } else
            getRoutesFromSaved()
    }

    private fun getRoutesFromSaved() {
        TODO("Not yet implemented")
    }

    private fun getRoutesFromApi() {

        CoroutineScope(Dispatchers.IO).launch {

                val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
//                    .addConverterFactory(Conve)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val a = retrofit.create(RouteService::class.java)

                try {

                    val res = a.getRoutes().execute()
                    if (res.isSuccessful) {
                        val parser = GPXParser()


                        for (route in res.body()?.routes.orEmpty()) {
                            val res2 =
                                a.getRouteGpx(route.zone_id, route.id).execute()
                                    .body()?.byteStream()
                            if (res2 == null) {
                                continue
                            }

                            var gpsPoints = emptyList<GpsPoint>()

                            try {
                                val parsedGpx: Gpx? = parser.parse(res2)
                                val trackPoints = parsedGpx?.tracks?.firstOrNull()
                                    ?.trackSegments?.firstOrNull()
                                    ?.trackPoints.orEmpty()

                                gpsPoints = trackPoints.map { GpsPoint(it.latitude, it.longitude) }


                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                            Log.e(TAG, "getRoutesFromApi: ${gpsPoints.size}", )
                            routes.add(
                                Route(
                                    id = route.id,
                                    zoneId = route.zone_id,
                                    images = route.img_urls.map { BASE_URL+it },
                                    name = route.name,
                                    duration = route.duration,
                                    description = route.desc,
                                    route = GpsRoute(gpsPoints)
                                )
                            )
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                Log.e("MEMES ", routes.size.toString())
            }

    }

    override suspend fun getRoutes(): List<Route> {
        return routes
    }

    override suspend fun getRoutes(zoneId: Int): List<Route> {
        return routes.filter { it.zoneId == zoneId }
    }

    override suspend fun getRouteGps(routeId: Int): GpsRoute {
        TODO("Not yet implemented")
    }
}


