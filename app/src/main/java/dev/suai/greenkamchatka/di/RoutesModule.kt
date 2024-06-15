package dev.suai.greenkamchatka.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.suai.greenkamchatka.data.routes.RouteRepository
import dev.suai.greenkamchatka.data.routes.impl.RouteRepositoryApi
import dev.suai.greenkamchatka.data.routes.impl.RouteService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoutesModule {

    @Provides
    @Singleton
    fun provideRoutesApi(app: Application): RouteRepository {
        return RouteRepositoryApi(app)
    }
}