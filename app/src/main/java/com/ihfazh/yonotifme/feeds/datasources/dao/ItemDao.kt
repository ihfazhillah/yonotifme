package com.ihfazh.yonotifme.feeds.datasources.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ihfazh.yonotifme.feeds.datasources.entities.FeedItemEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface ItemDao {
    @Query("select * from feed")
    fun list(): Flowable<List<FeedItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(feedItem: FeedItemEntity): Completable

    @Query("select * from feed where guid = :id")
    fun detail(id: String): Flowable<FeedItemEntity>
}