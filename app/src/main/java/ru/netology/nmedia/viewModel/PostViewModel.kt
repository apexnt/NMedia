package ru.netology.nmedia.viewModel

import androidx.lifecycle.ViewModel
import ru.netology.nmedia.data.PostRepository
import ru.netology.nmedia.data.imp.PostRepositoryInMemoryImpl

class PostViewModel : ViewModel() {
    private val repository : PostRepository = PostRepositoryInMemoryImpl()

    val data by repository::data

    fun onLikeClicked() = repository.like()

    fun onShareClicked() = repository.share()

}