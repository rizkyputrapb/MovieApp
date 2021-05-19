package com.polinema.movieapp.utils.api

import com.polinema.movieapp.utils.api.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object ApiBuilder {
    var builder =
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) as Retrofit.Builder
    var retrofit = builder.build() as Retrofit
    var logging =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    var httpClient = OkHttpClient.Builder()

    @Provides
    fun createService(): ApiInterface {
        if (!httpClient.interceptors().contains(logging)) {
            httpClient.addInterceptor(logging)
            builder.client(httpClient.build())
            retrofit = builder.build()
        }
        return retrofit.create(ApiInterface::class.java)
    }
}