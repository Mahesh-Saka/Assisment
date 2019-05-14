package com.greenlight.assisgment.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.greenlight.assisgment.callback.ApiInterface
import com.greenlight.assisgment.utils.AppController
import com.greenlight.assisgment.database.AppDatabase
import com.greenlight.assisgment.entity.UserModel
import com.greenlight.assisgment.database.UserModelDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executors

class UserRepository(var context: Context) {
    var apiInterface: ApiInterface = AppController.getRetrofilt().create(ApiInterface::class.java)
    var application: AppDatabase = AppDatabase.getAppDataBase(context)!!
    var userModelDao: UserModelDao = application!!.userModelDao()
    val service = Executors.newSingleThreadExecutor()

    fun getUserList() {
        val mutableLiveData = MutableLiveData<List<UserModel>>()
        val callList = apiInterface.getAllUserList()
        callList.enqueue(object : Callback<List<UserModel>> {
            override fun onResponse(call: Call<List<UserModel>>, response: Response<List<UserModel>>) {
                mutableLiveData.value = response.body()!!
                userModelDao.insertAll(response.body()!!)
                Log.e("Nice", "Success")
            }

            override fun onFailure(call: Call<List<UserModel>>, t: Throwable) {
                Log.e("Failed", t.message)
            }
        })
    }

    fun deleteUser(userModel: UserModel) {
        service.execute {
            userModelDao.deleteUser(userModel)
        }
    }
}
