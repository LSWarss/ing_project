package com.lswarss.ing_project.domain

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "Posts")
@Parcelize
data class PostItem(
    val body: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val userId: Int
) : Parcelable