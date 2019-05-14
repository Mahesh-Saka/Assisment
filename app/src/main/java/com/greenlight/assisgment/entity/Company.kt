package com.greenlight.assisgment.entity

import androidx.room.ColumnInfo

data class Company(
    @ColumnInfo(name = "com_name")
    val name: String,
    val catchPhrase: String,
    val bs: String
)