package com.greenlight.assisgment.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.greenlight.assisgment.entity.UserModel

@Dao
interface UserModelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(userModel: UserModel)

    @Delete
    fun deleteUser(userModel: UserModel)

    @Query("SELECT * FROM userTable ")
    fun getAllUserModel(): LiveData<List<UserModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(userModelList: List<UserModel>)
}