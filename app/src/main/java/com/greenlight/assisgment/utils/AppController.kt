package com.greenlight.assisgment.utils

import android.app.Application
import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppController : Application() {

    override fun onCreate() {
        super.onCreate()
        context1 = this
    }

    companion object {
        var context1: Context? = null
        var retrofit: Retrofit? = null
        fun getRetrofilt(): Retrofit {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            }
            return retrofit!!
        }

        fun getContext(): Context {
            return context1!!
        }
    }
}