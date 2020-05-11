package com.lswarss.ing_project.db

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.lswarss.ing_project.domain.Address
import com.lswarss.ing_project.domain.Company
import com.lswarss.ing_project.domain.PostItem
import com.lswarss.ing_project.domain.UserItem

class Converters {

    @TypeConverter
    fun fromUser(user : UserItem) : String {
        return user.name
    }

    @TypeConverter
    fun toUser(address: Address, company : Company, email: String, id: Int, name: String) : UserItem {
        return UserItem(address, company, email, id, name, name, name , name)
    }

    @TypeConverter
    fun fromPost(post : PostItem) : String {
        return post.title
    }

    @TypeConverter
    fun toPost(body: String, id : Int, title : String, userId: Int) : PostItem {
        return PostItem(body, id, title, userId)
    }


}