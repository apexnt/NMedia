package ru.netology.nmedia.data.imp

import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.data.PostRepository
import ru.netology.nmedia.dto.Post

class PostRepositoryInMemoryImpl : PostRepository {

    override val data = MutableLiveData(
        Post(
            id = 1L,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов " +
                    "по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике " +
                    "и управлению. Мы растём сами и помогаем расти студентам: от новичков " +
                    "до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, " +
                    "что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, " +
                    "бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку " +
                    "перемен → http://netolo.gy/fyb\"",
            publisher = "22.06.2022",
            likeCount = 999,
            shareCount = 999,
            likeByMe = false
        )
    )

    override fun like() {
        val currentPost = checkNotNull(data.value) {
            "Data values  should not be null"
        }
        val count = data.value!!.likeCount
        val likePost = currentPost.copy(
            likeByMe = !currentPost.likeByMe, likeCount = if (!currentPost.likeByMe) {
                count + 1
            } else count - 1
        )
        data.value = likePost
    }

    override fun share() {
        val currentPost = checkNotNull(data.value) {
            "Data values  should not be null"
        }
        val count = data.value!!.shareCount
        val counterShare = currentPost.copy(
            shareCount = count + 1
        )
        data.value = counterShare
    }
}




