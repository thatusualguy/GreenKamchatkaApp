package dev.suai.greenkamchatka.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.suai.greenkamchatka.data.zones.ZonesRepository
import dev.suai.greenkamchatka.data.zones.impl.ZonesRepositoryHardcoded
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ZonesModule {

    @Provides
    @Singleton
    fun provideZonesRepo(app: Application): ZonesRepository {
        return ZonesRepositoryHardcoded(app)
    }
}