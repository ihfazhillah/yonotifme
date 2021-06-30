package com.ihfazh.yonotifme.feeds.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val title: String,
    val guid: String,
    val url: String,
    val description: String,
    val tags: List<Tag>? = null,
    var isRead: Boolean = false
)
