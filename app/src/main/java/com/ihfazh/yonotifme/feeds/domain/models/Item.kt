package com.ihfazh.yonotifme.feeds.domain.models

data class Item(
    val title: String,
    val guid: String,
    val url: String,
    val description: String,
    val tags: List<Tag>? = null,
    var isRead: Boolean = false
)
