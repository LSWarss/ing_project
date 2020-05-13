package com.lswarss.ing_project.domain

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class UserWithItem(
    @PrimaryKey(autoGenerate = true) val userWithItemId : Int? = null,
    @Embedded val user: UserItem,
    @Embedded(prefix = "post_")val post: PostItem) : Parcelable