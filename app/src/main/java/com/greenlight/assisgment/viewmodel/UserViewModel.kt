package com.greenlight.assisgment.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.greenlight.assisgment.utils.AppUtil
import com.greenlight.assisgment.entity.UserModel
import com.greenlight.assisgment.repository.UserRepository

class UserViewModel(var context: Application) : AndroidViewModel(context) {
    var userRepository = UserRepository(context)

    fun getAllUserList(): LiveData<List<UserModel>> {
        if (AppUtil.isInternetConnected(context)) userRepository.getUserList()
        return userRepository.userModelDao.getAllUserModel()
    }

    fun delete(userModel: UserModel) {
        userRepository.deleteUser(userModel)
    }
}