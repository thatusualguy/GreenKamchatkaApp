package dev.suai.greenkamchatka.data.reports.impl

import android.content.Context
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import dev.suai.greenkamchatka.data.reports.Report
import dev.suai.greenkamchatka.data.reports.ReportRepository
import dev.suai.greenkamchatka.data.reports.impl.room.ReportDao
import dev.suai.greenkamchatka.service.ReportWorker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class ReportRepositoryCombined @Inject constructor(
    private val dao: ReportDao,
    private val context: Context
) : ReportRepository {


    init {

    }

    private fun enqueueReportWork(context: Context) {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val reportWorkRequest = OneTimeWorkRequestBuilder<ReportWorker>()
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(context).enqueue(reportWorkRequest)
    }

    override suspend fun sendReport(report: Report) {
        withContext(Dispatchers.IO) {
            dao.insert(report)
            enqueueReportWork(context)
        }
    }

    override fun canSendReportNow(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getUnsentReports(): List<Report> {
        return dao.getAllReports()
    }

    override suspend fun trySendingReports(): Boolean {
        TODO("Not yet implemented")
    }

}