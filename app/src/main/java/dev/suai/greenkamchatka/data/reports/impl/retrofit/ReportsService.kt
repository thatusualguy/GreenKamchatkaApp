package dev.suai.greenkamchatka.data.reports.impl.retrofit

import retrofit2.Call
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ReportsService {
    @Multipart
    @POST("api/mapp/send_report")
    fun sendReport(
        @Part type: MultipartBody.Part,
        @Part location: MultipartBody.Part,
        @Part comment: MultipartBody.Part,
        @Part time: MultipartBody.Part,
        @Part phone: MultipartBody.Part,
        @Part email: MultipartBody.Part,
        @Part image: MultipartBody.Part
    ) : Call<ResponseApi>
}