package com.ihfazh.yonotifme.feeds.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ihfazh.yonotifme.feeds.datasources.local.dao.ItemDao
import com.ihfazh.yonotifme.feeds.datasources.local.entities.FeedItemEntity

@Database(
    entities = [FeedItemEntity::class],
    version = 1,
    exportSchema = true
)
abstract class FeedDatabase: RoomDatabase() {
    abstract fun feedItemDao(): ItemDao
}