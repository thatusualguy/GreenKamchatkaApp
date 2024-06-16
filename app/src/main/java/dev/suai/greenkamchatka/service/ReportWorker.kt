package dev.suai.greenkamchatka.service

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import androidx.room.Room
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dev.suai.greenkamchatka.data.reports.Report
import dev.suai.greenkamchatka.data.reports.impl.retrofit.ReportsService
import dev.suai.greenkamchatka.data.reports.impl.room.ReportDatabase
import dev.suai.greenkamchatka.di.BASE_URL
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okio.BufferedSink
import okio.Okio
import okio.source
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.IOException
import javax.annotation.Nullable


class ReportWorker(private val appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        val database =
            Room.databaseBuilder(applicationContext, ReportDatabase::class.java, "ReportDatabase")
                .build()
        val reportDao = database.reportDao()

        val reports = reportDao.getAllReports()
        for (report in reports) {
            if (report.id == null)
                continue

            try {
                sendReport(report)
                reportDao.deleteReportById(report.id)
            } catch (e: Exception) {
                e.printStackTrace()
                return Result.retry()
            }
        }

        return Result.success()
    }

    private fun getFileFromUri(context: Context, uri: Uri): File? {
        var filePath: String? = null
        val contentResolver: ContentResolver = context.contentResolver
        val cursor: Cursor? = contentResolver.query(uri, null, null, null, null)
        cursor?.use {
            if (it.moveToFirst()) {
                val columnIndex = it.getColumnIndex(MediaStore.Images.Media.DATA)
                if (columnIndex != -1) {
                    filePath = it.getString(columnIndex)
                }
            }
        }
        return filePath?.let { File(it) }
    }

    fun meme(){
    }

    private fun prepareFilePart(context: Context, fileUri: Uri): MultipartBody.Part {

//        val file = getFileFromUri(context, fileUri)
//        val requestFile =
//            file?.asRequestBody(context.contentResolver.getType(fileUri)?.toMediaTypeOrNull())
        return MultipartBody.Part.createFormData("image", "a", ContentUriRequestBody( context.contentResolver, fileUri))
    }


    private suspend fun sendReport(report: Report) {


        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val a = retrofit.create(ReportsService::class.java)

        var b: MultipartBody.Part? = null
        if (report.imageUri?.path != null)
            b = prepareFilePart(appContext, report.imageUri)


        val type = listOf(
            "Мусор",
            "Кострища",
            "Браконьерство",
            "Пожары",
            "Другое"
        )[report.type.minus(1).coerceAtMost(4).coerceAtLeast(0)]

        a.sendReport(
            type = type,
            email = report.email,
            comment = report.comment,
            phone = report.phone,
            time = report.time.toString(),
            image = b ?: MultipartBody.Part.createFormData("image", ""),
            location = report.location.lat.toString() + " " + report.location.lon.toString()
        ).execute()
    }
}


class ContentUriRequestBody(
    private val contentResolver: ContentResolver,
    private val contentUri: Uri
) : RequestBody() {

    override fun contentType(): MediaType? {
        val contentType = contentResolver.getType(contentUri) ?: return null
        return contentType.toMediaTypeOrNull()
    }

    override fun writeTo(sink: BufferedSink) {
        val inputStream = contentResolver.openInputStream(contentUri)
            ?: throw IllegalStateException("Couldn't open content URI for reading: $contentUri")

        inputStream.source().use { source ->
            sink.writeAll(source)
        }
    }
}