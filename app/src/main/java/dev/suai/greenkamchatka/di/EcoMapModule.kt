package dev.suai.greenkamchatka.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.suai.greenkamchatka.data.map.EcoMapRepository
import dev.suai.greenkamchatka.data.map.impl.EcoMapRepositoryApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class EcoMapModule {

    @Provides
    @Singleton
    fun provideEcoMapApi(app: Application): EcoMapRepository {
        return EcoMapRepositoryApi(app)
    }
}