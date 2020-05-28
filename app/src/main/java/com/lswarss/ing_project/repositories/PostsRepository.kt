package com.lswarss.ing_project.repositories

import com.lswarss.ing_project.db.PostsDatabase
import com.lswarss.ing_project.domain.UserWithItem

class PostsRepository (val db : PostsDatabase){

    suspend fun upsert(userWithItem: UserWithItem) = db.getUserWithItemDao().upsert(userWithItem)

    fun getSavedPosts() = db.getUserWithItemDao().getAllUserWithItem()

    suspend fun delete(userWithItem: UserWithItem) = db.getUserWithItemDao().deleteUserWithItem(userWithItem)

}