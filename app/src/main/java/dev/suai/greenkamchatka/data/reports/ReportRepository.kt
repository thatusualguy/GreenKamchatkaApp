package dev.suai.greenkamchatka.data.reports

interface ReportRepository {
    suspend fun sendReport(report: Report)
    fun canSendReportNow(): Boolean
    suspend fun getUnsentReports(): List<Report>
    suspend fun trySendingReports(): Boolean
}