package com.hyeonbinni.data.network

import com.hyeonbinni.data.model.GetCompanyResponse
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface ApiService {

    @GET("/company_search/{query}/{page}")
    fun getCompanySearch(
        @Query("query") query: String,
        @Query("page") page: Int
    ): Single<GetCompanyResponse>

    @GET("/company_review/{company_id}/{page}")
    fun getCompanyReview(
        @Query("company_id") companyId: Int,
        @Query("page") page: Int
    ): Single<GetCompanyResponse>

    companion object {
        private const val BASE_URL = "http://base_url.com"

        fun create(): ApiService {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}
