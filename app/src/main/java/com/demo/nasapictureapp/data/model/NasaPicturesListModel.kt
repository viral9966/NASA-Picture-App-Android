package com.demo.nasapictureapp.data.model

class NasaPicturesListModel : ArrayList<NasaPicturesListModelItem>()

data class NasaPicturesListModelItem(
    val copyright: String,
    val date: String,
    val explanation: String,
    val hdurl: String,
    val media_type: String,
    val service_version: String,
    val title: String,
    val url: String
)