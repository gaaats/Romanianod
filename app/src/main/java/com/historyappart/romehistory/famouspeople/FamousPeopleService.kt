package com.historyappart.romehistory.famouspeople

import com.historyappart.romehistory.BuildConfig
import okhttp3.Interceptor
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FamousPeopleService {

    companion object {
        const val BASE_URL = "https://api.api-ninjas.com/v1/"
        const val API_KEY = BuildConfig.API_KEY
        const val PERSON_NAME = "Caesar"
    }


    @GET("historicalfigures")
    suspend fun getEvents(
        @Query("name") name : String = PERSON_NAME,
    ): Response<FamousPersonList>


}

class MyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("X-Api-Key", FamousPeopleService.API_KEY)
            .build()
        return chain.proceed(request)
    }
}