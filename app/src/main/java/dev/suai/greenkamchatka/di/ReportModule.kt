package dev.suai.greenkamchatka.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.suai.greenkamchatka.data.reports.ReportRepository
import dev.suai.greenkamchatka.data.reports.impl.ReportRepositoryCombined
import dev.suai.greenkamchatka.data.reports.impl.room.ReportDatabase
import dev.suai.greenkamchatka.data.visitors.impl.VisitorDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ReportModule {

    @Provides
    @Singleton
    fun provideReportDatabase(app: Application): ReportDatabase {
        return Room.databaseBuilder(
            app,
            ReportDatabase::class.java,
            "ReportDatabase"
        )
            .fallbackToDestructiveMigration()
            .build()
    }


    @Singleton
    @Provides
    fun provideReportRepository(app: Application, database: ReportDatabase): ReportRepository {
        return ReportRepositoryCombined(database.reportDao(), app)
    }
}