package dev.suai.greenkamchatka.data.map.impl

import android.app.Application
import dev.suai.greenkamchatka.data.GpsPoint
import dev.suai.greenkamchatka.data.map.EcoMapRepository
import dev.suai.greenkamchatka.data.map.ReportMarker
import dev.suai.greenkamchatka.di.BASE_URL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EcoMapRepositoryApi(private val app: Application) : EcoMapRepository {
    private val reportMarkers: Flow<List<ReportMarker>> = flow {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val a = retrofit.create(EcoMapService::class.java)


        emit(a.getReports().execute().body()?.reports.orEmpty().map {
            ReportMarker(
                it.status_name, it.status_id, it.type_id, it.type_of_report, GpsPoint(
                    it.coords[0].toDouble(),
                    it.coords[1].toDouble()
                )
            )
        })


    }.flowOn(Dispatchers.IO)

    override fun getAll(): Flow<List<ReportMarker>> {
        return reportMarkers
    }
}