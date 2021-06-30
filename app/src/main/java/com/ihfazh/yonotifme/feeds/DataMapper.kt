package com.ihfazh.yonotifme.feeds

import com.ihfazh.yonotifme.feeds.datasources.entities.FeedItemEntity
import com.ihfazh.yonotifme.feeds.domain.models.Item

class DataMapper {
    companion object {
        fun feedItemMapper(item: Item) : FeedItemEntity{
            return FeedItemEntity(
                item.guid,
                item.title,
                item.url,
                item.description
            )
        }

        fun feedEntityMapper(entity: FeedItemEntity): Item {
            return Item(
                entity.title,
                entity.guid,
                entity.url,
                entity.description,
                null,
                false
            )
        }
    }

}
