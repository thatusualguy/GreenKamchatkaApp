package dev.suai.greenkamchatka.data.reports.impl.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.suai.greenkamchatka.data.reports.Converters
import dev.suai.greenkamchatka.data.reports.Report

@Database(entities = [Report::class], version = 1)
@TypeConverters(Converters::class)
abstract class ReportDatabase : RoomDatabase() {
    abstract fun reportDao(): ReportDao
}