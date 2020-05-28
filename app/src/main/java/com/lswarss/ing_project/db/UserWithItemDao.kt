package com.lswarss.ing_project.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.lswarss.ing_project.domain.UserWithItem

@Dao
interface UserWithItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(userWithItem: UserWithItem): Long

    @Query("SELECT * FROM userwithitem")
    fun getAllUserWithItem(): LiveData<List<UserWithItem>>

    @Delete
    suspend fun deleteUserWithItem(userWithItem: UserWithItem)
}