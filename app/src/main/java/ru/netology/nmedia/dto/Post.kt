package ru.netology.nmedia.dto

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val publisher: String,
    var likeByMe: Boolean = false,
    var likeCount: Int = 0,
    var share: Boolean = false,
    var shareCount: Int = 0
)
