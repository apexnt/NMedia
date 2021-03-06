package ru.netology.nmedia.data.imp

import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.data.PostRepository
import ru.netology.nmedia.dto.Post

class PostRepositoryInMemoryImpl : PostRepository {

    private var nextId = GENERATED_POSTS_AMOUNT.toLong()

    private val posts
        get() = checkNotNull(data.value) {
            "Data values  should not be null"
        }

    override val data = MutableLiveData(
        List(GENERATED_POSTS_AMOUNT) { index ->
            Post(
                id = index + 1L,
                author = "Нетология. Университет интернет-профессий будущего",
                content = "$index Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов " +
                        "по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике " +
                        "и управлению. Мы растём сами и помогаем расти студентам: от новичков " +
                        "до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, " +
                        "что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, " +
                        "бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку " +
                        "перемен → http://netolo.gy/fyb\"",
                publisher = "02.07.2022",
                likeCount = 1999999,
                shareCount = 0,
                likeByMe = false ,
                video = "https://www.youtube.com/watch?v=acRKY2BNusc"
            )
        }
    )

    override fun like(postId: Long) {
        data.value = posts.map {
            if (it.id != postId) it
            else it.copy(
                likeByMe = !it.likeByMe, likeCount = if (it.likeByMe) it.likeCount - 1
                else {
                    it.likeCount + 1
                }
            )
        }
    }

    override fun share(postId: Long) {
        data.value = posts.map {
            if (it.id != postId) it
            else it.copy(
                shareCount = it.shareCount + 1
            )
        }
    }

    override fun deleteById(postId: Long) {
        data.value = posts.filter { it.id != postId }
    }

    override fun save(post: Post) {
        if (post.id == PostRepository.NEW_POST_ID) insert(post) else update(post)
    }

    private fun insert(post: Post) {
        data.value = listOf(
            post.copy(id = ++nextId)
        ) + posts
    }

    private fun update(post: Post) {
        data.value = posts.map {
            if (it.id == post.id) post else it
        }
    }

    private companion object {
        const val GENERATED_POSTS_AMOUNT = 1000
    }

}


