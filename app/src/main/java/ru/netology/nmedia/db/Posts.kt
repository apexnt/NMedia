package ru.netology.nmedia.db

import android.database.Cursor
import ru.netology.nmedia.dto.Post

fun Cursor.toPost() = Post(
    id = getLong(getColumnIndexOrThrow(PostsTable.Column.ID.columnName)),
    author = getString(getColumnIndexOrThrow(PostsTable.Column.AUTHOR.columnName)),
    content = getString(getColumnIndexOrThrow(PostsTable.Column.CONTENT.columnName)),
    publisher = getString(getColumnIndexOrThrow(PostsTable.Column.PUBLISHED.columnName)),
    likeCount = getInt(getColumnIndexOrThrow(PostsTable.Column.LIKE_COUNT.columnName)),
    likeByMe = getInt(getColumnIndexOrThrow(PostsTable.Column.LIKE_BY_ME.columnName)) != 0,
    shareCount = getInt(getColumnIndexOrThrow(PostsTable.Column.SHARE_COUNT.columnName)),
    video = getString(getColumnIndexOrThrow(PostsTable.Column.VIDEO.columnName))
)