package dev.suai.greenkamchatka.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.suai.greenkamchatka.data.permits.PermitsRepo
import dev.suai.greenkamchatka.data.permits.impl.PermitsRepoApi
import dev.suai.greenkamchatka.data.permits.impl.PermitsService
import dev.suai.greenkamchatka.data.reports.impl.retrofit.ReportsService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PermitsModule {

    @Provides
    @Singleton
    fun providePermitsApi(): PermitsService {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(PermitsService::class.java)
    }


    @Singleton
    @Provides
    fun providePermitsRepo(permitsService: PermitsService): PermitsRepo {
        return PermitsRepoApi(permitsService)
    }
}