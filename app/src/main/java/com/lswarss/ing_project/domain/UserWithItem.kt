package com.lswarss.ing_project.domain

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserWithItem(
    @Embedded val user: UserItem,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val post: PostItem) : Parcelable