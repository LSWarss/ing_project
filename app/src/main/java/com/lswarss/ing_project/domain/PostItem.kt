package com.lswarss.ing_project.domain

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
data class PostItem(
    val body: String?,
    val id: Int,
    val title: String?,
    val userId: Int
) : Parcelable