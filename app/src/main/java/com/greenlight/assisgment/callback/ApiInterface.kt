package com.greenlight.assisgment.callback

import com.greenlight.assisgment.entity.UserModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("users")
    fun getAllUserList(): Call<List<UserModel>>
}