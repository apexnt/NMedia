package ru.netology.nmedia.data.imp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.PostBinding
import ru.netology.nmedia.dto.Counter
import ru.netology.nmedia.dto.Post

typealias OnPostLikeClicked = (Post) -> Unit
typealias OnPostShareClicked = (Post) -> Unit

internal class PostsAdapter(
    private val onLikeClicked: OnPostLikeClicked, private val onShareClicked: OnPostShareClicked
) : ListAdapter<Post, PostsAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PostBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

   inner class ViewHolder(
        private val binding: PostBinding
    ) : RecyclerView.ViewHolder(binding.root) {

       private lateinit var post: Post

       init {
           binding.likes.setOnClickListener { onLikeClicked(post) }
           binding.sharePost.setOnClickListener { onShareClicked(post) }
       }

        fun bind(post: Post) {
            this.post = post
            with(binding) {
                authorName.text = post.author
                content.text = post.content
                date.text = post.publisher
                likes.setImageResource(getLikeIconResId(post.likeByMe))
                totalRepost.text = Counter().counterConversion(post.shareCount)
                totalLikes.text = Counter().counterConversion(post.likeCount)
            }
        }

        @DrawableRes
        private fun getLikeIconResId(liked: Boolean) =
            if (liked) R.drawable.ic_like_red_24 else R.drawable.ic_like_24
    }

    private object DiffCallback : DiffUtil.ItemCallback<Post>() {

        override fun areItemsTheSame(oldItem: Post, newItem: Post) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Post, newItem: Post) =
            oldItem == newItem
    }
}