package com.historyappart.romehistory.utils

import com.historyappart.romehistory.BuildConfig
import com.historyappart.romehistory.famouspeople.FamousPeopleService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NevsApiModule {

    /// it is faaaaake
    @Provides
    @Singleton
    fun providesNevsAPIService(): FamousPeopleService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.API_KEY)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(FamousPeopleService::class.java)
    }

    fun fakeFun(){
        providesNevsAPIService()
    }
}