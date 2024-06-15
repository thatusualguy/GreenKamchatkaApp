package dev.suai.greenkamchatka.data.reports

interface ReportRepository {
    suspend fun sendReport(report: Report)
    fun canSendReportNow(): Boolean
    suspend fun getUnsentReports(): List<Int>
    suspend fun trySendingReports(): Boolean
}