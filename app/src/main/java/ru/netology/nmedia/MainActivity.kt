package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Counter
import ru.netology.nmedia.dto.Post


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
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
            likeCount = 0,
            shareCount = 0,
            likeByMe = false
        )

        with(binding) {
            authorName.text = post.author
            content.text = post.content
            date.text = post.publisher
            totalLikes.text = post.likeCount.toString()
            totalRepost.text = post.shareCount.toString()
            if (post.likeByMe) {
                likes.setImageResource(R.drawable.ic_like_24)
            }
            likes.setOnClickListener {
                post.likeByMe = !post.likeByMe
                likes.setImageResource(
                    if (post.likeByMe) R.drawable.ic_like_red_24 else R.drawable.ic_like_24
                )

                val counterLike = post.likeCount + 1
                if (post.likeByMe) totalLikes.text =
                    Counter().counterConversion(counterLike) else totalLikes.text =
                    Counter().counterConversion(post.likeCount)
            }

            sharePost.setOnClickListener {
                val counterShare = post.shareCount++
                totalRepost.text = Counter().counterConversion(counterShare)
            }
        }
    }
}


