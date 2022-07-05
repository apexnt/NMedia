package ru.netology.nmedia.data.imp

import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.data.PostRepository
import ru.netology.nmedia.dto.Post

class PostRepositoryInMemoryImpl : PostRepository {

    private val posts
        get() = checkNotNull(data.value) {
            "Data values  should not be null"
        }

    override val data = MutableLiveData(
        List(100) { index ->
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
                likeCount = 1099,
                shareCount = 1099,
                likeByMe = false
            )
        }
    )

    override fun like(postId: Long) {
        data.value = posts.map {
            if (it.id != postId) it
            else it.copy(
                likeByMe = !it.likeByMe, likeCount = if (it.likeByMe) it.likeCount -1
                else {
                    it.likeCount +1
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
}


