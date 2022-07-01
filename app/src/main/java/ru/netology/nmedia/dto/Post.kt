package ru.netology.nmedia.dto

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val publisher: String,
    val likeByMe: Boolean = false,
    val likeCount: Int = 0,
    val share: Boolean = false,
    val shareCount: Int = 0
)
