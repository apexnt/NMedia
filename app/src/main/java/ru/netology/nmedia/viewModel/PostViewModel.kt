package ru.netology.nmedia.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.nmedia.adapter.PostInteractionListener
import ru.netology.nmedia.data.PostRepository
import ru.netology.nmedia.data.imp.PostRepositoryInMemoryImpl
import ru.netology.nmedia.dto.Post

class PostViewModel : ViewModel(), PostInteractionListener {

    private val repository: PostRepository = PostRepositoryInMemoryImpl()

    val data by repository::data

    val currentPost = MutableLiveData<Post?>(null)

    fun onSaveButtonClicked(content: String) {
        if (content.isBlank()) return
        val post = currentPost.value?.copy(
            content = content
        ) ?: Post(
            id = PostRepository.NEW_POST_ID,
            author = "Me",
            content = content,
            publisher = "Today"
        )
        repository.save(post)
        currentPost.value = null
    }

    fun onCancelEditButtonClicked() {
        currentPost.value?.let {
            currentPost.value = currentPost.value
        }
    }

    // region PostInteractionListener

    override fun onLikeClicked(post: Post) = repository.like(post.id)

    override fun onShareClicked(post: Post) = repository.share(post.id)

    override fun onDeleteClicked(post: Post) = repository.deleteById(post.id)

    override fun onEditClicked(post: Post) {
        currentPost.value = post
    }


    // endregion PostInteractionListener
}
