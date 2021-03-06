package com.lswarss.ing_project.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserItem(
    val address: Address,
    val company: Company,
    val email: String?,
    val id: Int,
    val fullName: String?,
    val phone: String?,
    val username: String?,
    val website: String?
) : Parcelable