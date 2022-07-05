package ru.netology.nmedia.data.imp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.PostBinding
import ru.netology.nmedia.dto.Counter
import ru.netology.nmedia.dto.Post
import kotlin.properties.Delegates

internal class PostsAdapter(
    private val onLikeClicked: (Post) -> Unit, private val onShareClicked: (Post) -> Unit
) : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    var posts: List<Post> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PostBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount() = posts.size

   inner class ViewHolder(
        private val binding: PostBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) = with(binding) {
            authorName.text = post.author
            content.text = post.content
            date.text = post.publisher
            likes.setImageResource(getLikeIconResId(post.likeByMe))
            likes.setOnClickListener { onLikeClicked(post) }
            sharePost.setOnClickListener { onShareClicked(post) }
            totalRepost.text = Counter().counterConversion(post.shareCount)
            totalLikes.text = Counter().counterConversion(post.likeCount)
        }

        @DrawableRes
        private fun getLikeIconResId(liked: Boolean) =
            if (liked) R.drawable.ic_like_red_24 else R.drawable.ic_like_24
    }
}