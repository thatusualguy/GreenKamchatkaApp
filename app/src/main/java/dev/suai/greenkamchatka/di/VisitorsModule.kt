package dev.suai.greenkamchatka.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.suai.greenkamchatka.data.visitors.VisitorsRepository
import dev.suai.greenkamchatka.data.visitors.impl.VisitorDatabase
import dev.suai.greenkamchatka.data.visitors.impl.VisitorsRepositoryLocal
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object VisitorsModule {

    @Provides
    @Singleton
    fun provideVisitorDatabase(app: Application): VisitorDatabase {
        return Room.databaseBuilder(
            app,
            VisitorDatabase::class.java,
            "VisitorDatabase"
        )
            .fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    @Singleton
    fun provideVisitorRepository(database: VisitorDatabase): VisitorsRepository {
        return VisitorsRepositoryLocal(database.dao)
    }
}
