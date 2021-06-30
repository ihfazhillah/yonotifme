package com.ihfazh.yonotifme.feeds.datasources

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ihfazh.yonotifme.feeds.datasources.dao.ItemDao
import com.ihfazh.yonotifme.feeds.datasources.entities.FeedItemEntity

@Database(
    entities = [FeedItemEntity::class],
    version = 1,
    exportSchema = true
)
abstract class FeedDatabase: RoomDatabase() {
    abstract fun feedItemDao(): ItemDao
}