package dev.suai.greenkamchatka.data.reports.impl.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.suai.greenkamchatka.data.reports.Report

@Dao
interface ReportDao {
    @Insert
    suspend fun insert(report: Report)

    @Query("SELECT * FROM report WHERE id = :id")
    suspend fun getReportById(id: Int): Report?

    @Query("DELETE FROM report WHERE id = :id")
    suspend fun deleteReportById(id: Int)

    @Query("SELECT * FROM report")
    suspend fun getAllReports(): List<Report>
}
