package com.lswarss.ing_project.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserWithItem(val user: UserItem, val post: PostItem) : Parcelable