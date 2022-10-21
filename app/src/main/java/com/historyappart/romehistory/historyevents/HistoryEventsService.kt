package com.historyappart.romehistory.historyevents

import com.historyappart.romehistory.BuildConfig
import okhttp3.Interceptor
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HistoryEventsService {

    companion object {
        const val BASE_URL = "https://api.api-ninjas.com/v1/"
        const val API_KEY = BuildConfig.API_KEY
        const val TEXT_QUERRY = "rome"
    }


    @GET("historicalevents")
    suspend fun getEvents(
        @Query("offset") offset: String = "30",
        @Query("text") text: String = TEXT_QUERRY,

    ): Response<ListEvents>


}

class MyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("X-Api-Key", HistoryEventsService.API_KEY)
            .build()
        return chain.proceed(request)
    }
}