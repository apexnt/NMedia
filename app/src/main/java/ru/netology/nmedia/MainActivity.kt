package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import ru.netology.nmedia.adapter.PostsAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.util.hideKeyboard
import ru.netology.nmedia.util.showKeyboard
import ru.netology.nmedia.viewModel.PostViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<PostViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = PostsAdapter(viewModel)
        binding.postsRecyclerView.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }

        binding.saveButton.setOnClickListener {
            with(binding.contentEditText) {
                val content = binding.contentEditText.text.toString()
                viewModel.onSaveButtonClicked(content)
            }
        }

        binding.cancelButton.setOnClickListener {
            with(binding.contentEditText) {
                viewModel.onCancelEditButtonClicked()
                val content = binding.contentEditText.text.toString()
                viewModel.onSaveButtonClicked(content)
            }
        }

        viewModel.currentPost.observe(this) { currentPost ->
            with(binding.contentEditText) {
                val content = currentPost?.content
                setText(content)
                if (content != null) {
                    binding.contentGroup.visibility = View.VISIBLE
                    requestFocus()
                    showKeyboard()
                } else {
                    binding.contentGroup.visibility = View.GONE
                    clearFocus()
                    hideKeyboard()
                }
            }
        }
    }
}


