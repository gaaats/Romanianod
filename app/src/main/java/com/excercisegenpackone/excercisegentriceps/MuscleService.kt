package com.excercisegenpackone.excercisegentriceps

import com.excercisegenpackone.excercisegentriceps.recycler.ExercicesListrRsponse
import okhttp3.Interceptor
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MuscleService {

    companion object {
        const val BASE_URL = "https://api.api-ninjas.com/v1/"
        const val API_KEY = BuildConfig.API_KEY
        const val MUSCLE_GROUP = "triceps"
    }


    @GET("exercises")
    suspend fun getExercises(
        @Query("muscle") muscle: String = MUSCLE_GROUP,
    ): Response<ExercicesListrRsponse>


}

class MyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("X-Api-Key", MuscleService.API_KEY)
            .build()
        return chain.proceed(request)
    }
}