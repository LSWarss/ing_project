package com.lswarss.ing_project.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.lswarss.ing_project.domain.UserWithItem

@Dao
interface UserWithItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) //this insert will replace the data if conflicted
    suspend fun upsert(userWithItem: UserWithItem): Long

    @Transaction
    @Query("SELECT * FROM Users")
    fun getAllArticles() : LiveData<List<UserWithItem>>

    @Delete
    suspend fun deleteArticle(userWithItem: UserWithItem)


}