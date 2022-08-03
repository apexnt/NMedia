package ru.netology.nmedia.db

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import ru.netology.nmedia.dto.Post

class PostDaoImpl(
    private val db: SQLiteDatabase
) : PostDao {
    override fun getAll() = db.query(
        PostsTable.NAME,
        PostsTable.ALL_COLUMNS_NAMES,
        null, null, null, null,
        "${PostsTable.Column.ID.columnName} DESC"
    ).use { cursor ->
        List(cursor.count) {
            cursor.moveToNext()
            cursor.toPost()
        }
    }

    override fun save(post: Post): Post {
        val values = ContentValues().apply {
            put(PostsTable.Column.AUTHOR.columnName, post.author)
            put(PostsTable.Column.CONTENT.columnName, post.content)
            put(PostsTable.Column.PUBLISHED.columnName, post.publisher)
            put(PostsTable.Column.VIDEO.columnName, post.video)
        }
        val id = if (post.id != 0L) {
            db.update(
                PostsTable.NAME,
                values,
                "${PostsTable.Column.ID.columnName} = ?",
                arrayOf(post.id.toString())
            )
            post.id
        } else { // post.id = 0L
            db.insert(PostsTable.NAME, null, values)
        }
        return db.query(
            PostsTable.NAME,
            PostsTable.ALL_COLUMNS_NAMES,
            "${PostsTable.Column.ID.columnName} = ?",
            arrayOf(id.toString()),
            null, null, null
        ).use { cursor ->
            cursor.moveToNext()
            cursor.toPost()
        }
    }

    override fun likeById(id: Long) {
        db.execSQL(
            """
                UPDATE ${PostsTable.NAME} SET
                ${PostsTable.Column.LIKE_COUNT.columnName} = ${PostsTable.Column.LIKE_COUNT.columnName} + CASE WHEN ${PostsTable.Column.LIKE_BY_ME.columnName} THEN -1 ELSE 1 END,
                ${PostsTable.Column.LIKE_BY_ME.columnName} = CASE WHEN likeByMe THEN 0 ELSE 1 END
                WHERE ${PostsTable.Column.ID.columnName} = ?;
            """.trimIndent(),
            arrayOf(id)
        )
    }

    override fun share(id: Long) {
        db.execSQL(
            """
                UPDATE ${PostsTable.NAME} SET
                ${PostsTable.Column.SHARE_COUNT.columnName} = ${PostsTable.Column.SHARE_COUNT.columnName} + 1
                WHERE ${PostsTable.Column.ID.columnName} = ?;
            """.trimIndent(),
            arrayOf(id)
        )
    }

    override fun removeById(id: Long) {
        db.delete(
            PostsTable.NAME,
            "${PostsTable.Column.ID.columnName} = ?",
            arrayOf(id.toString())
        )
    }


}

