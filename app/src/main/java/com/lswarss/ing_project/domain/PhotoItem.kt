package com.lswarss.ing_project.domain

data class PhotoItem(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)