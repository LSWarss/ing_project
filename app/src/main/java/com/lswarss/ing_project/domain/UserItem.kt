package com.lswarss.ing_project.domain

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "Users")
@Parcelize
data class UserItem(
    @Embedded
    val address: Address,
    @Embedded
    val company: Company,
    val email: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @SerializedName("name")
    val fullName: String,
    val phone: String,
    val username: String,
    val website: String
) : Parcelable