package com.lswarss.ing_project.db

import androidx.room.TypeConverter
import com.lswarss.ing_project.domain.Address
import com.lswarss.ing_project.domain.Company
import com.lswarss.ing_project.domain.Geo

class Converters {

    @TypeConverter
    fun fromAddress(address: Address): String {
        return address.zipcode
    }

    @TypeConverter
    fun toAddress(address: String): Address {
        return Address(address, Geo(address, address), address, address, address)
    }

    @TypeConverter
    fun fromGeo(geo: Geo): String {
        return "lat: " + geo.lat + " lng: " + geo.lng
    }

    @TypeConverter
    fun toGeo(latlng: String): Geo {
        return Geo(latlng, latlng)
    }

    @TypeConverter
    fun fromCompany(company: Company): String {
        return company.name
    }

    @TypeConverter
    fun toCompany(name: String): Company {
        return Company(name, name, name)
    }


}