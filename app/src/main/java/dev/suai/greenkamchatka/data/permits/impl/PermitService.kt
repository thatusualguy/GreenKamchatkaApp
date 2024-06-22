package dev.suai.greenkamchatka.data.permits.impl

import dev.suai.greenkamchatka.data.permits.ApiStatus
import dev.suai.greenkamchatka.data.permits.Permit
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface PermitsService {
    @POST("api/mapp/visit_request")
    fun sendPermit(
        @Body permit: Permit
    ): Call<ApiStatus>
}