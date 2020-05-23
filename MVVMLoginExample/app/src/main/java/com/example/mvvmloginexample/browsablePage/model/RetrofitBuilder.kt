package com.example.mvvmloginexample.browsablePage.model

import dagger.Module
import dagger.Provides
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit


@Module
class RetrofitBuilder {

    @Provides
    fun getApiInterface(retroFit: Retrofit): Api {
        return retroFit.create<Api>(Api::class.java!!)
    }

    @Provides
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}