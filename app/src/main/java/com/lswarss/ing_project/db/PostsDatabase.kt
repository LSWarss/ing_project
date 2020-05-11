package com.lswarss.ing_project.db

import android.content.Context
import androidx.room.*
import com.lswarss.ing_project.domain.PostItem
import com.lswarss.ing_project.domain.UserItem
import com.lswarss.ing_project.domain.UserWithItem


@Database(
    entities = [UserItem::class,PostItem::class],
    version = 2,
    exportSchema = false
)
abstract class PostsDatabase : RoomDatabase() {

    abstract fun getUserWithItemDao() : UserWithItemDao

    companion object {
        @Volatile
        private var instance : PostsDatabase? = null
        private val LOCK = Any() //this will synchronize, that there is only 1 instance of our database

        /**
         * Everything that happens inside this block of code can be accessed by other threads,
         * that makes sure that there is only one instance o our database
         */
        operator fun invoke(context : Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also { instance = it}
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                PostsDatabase::class.java,
                "posts_db.db"
            ).build()

    }
}