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
        @Part("type") type: String,
        @Part("location") location: String,
        @Part("comment") comment: String,
        @Part("time") time: String,
        @Part("phone") phone: String,
        @Part("email") email: String,
        @Part image: MultipartBody.Part
    ) : Call<Void>
}