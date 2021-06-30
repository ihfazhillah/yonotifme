package com.ihfazh.yonotifme.feeds.datasources.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "feed")
data class FeedItemEntity(
    @PrimaryKey
    val guid: String,

    @ColumnInfo
    val title: String,

    @ColumnInfo
    val url: String,

    @ColumnInfo
    val description: String,
)
