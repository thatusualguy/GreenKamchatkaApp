package dev.suai.greenkamchatka.data.permits

import dev.suai.greenkamchatka.data.reports.Report
import retrofit2.http.POST

interface PermitsRepo{

    @POST("api/mapp/visit_request")
    suspend fun sendPermit(permit: Permit)
}