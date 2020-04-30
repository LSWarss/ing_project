package com.lswarss.ing_project.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AlbumItem(
    val id: Int,
    val title: String,
    val userId: Int
) : Parcelable