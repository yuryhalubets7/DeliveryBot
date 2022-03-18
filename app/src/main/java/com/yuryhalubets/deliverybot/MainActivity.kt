package com.yuryhalubets.deliverybot

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.yuryhalubets.deliverybot.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
        subscribeToViewStateUpdates()
    }

    private fun setupViews() {
        with(binding) {
            etBotInput.doAfterTextChanged {
                btnFindPath.isEnabled = !it.isNullOrBlank()
            }
            btnFindPath.setOnClickListener {
                val text = etBotInput.text?.toString()
                if (text.isNullOrBlank()) {
                    btnFindPath.isEnabled = false
                } else {
                    viewModel.findPath(text)
                }
            }
        }
    }

    private fun subscribeToViewStateUpdates() {
        viewModel.viewStateFlow
            .flowWithLifecycle(lifecycle)
            .onEach(::onViewStateChanged)
            .launchIn(lifecycleScope)
    }

    private fun onViewStateChanged(state: MainViewState) {
        with(binding) {
            val isLoading = state is MainViewState.Loading
            etBotInput.isEnabled = !isLoading
            btnFindPath.isEnabled = !isLoading
            tvResults.text = when (state) {
                MainViewState.Loading -> getString(R.string.loading)
                is MainViewState.Success -> getString(R.string.success_result, state.path)
                is MainViewState.Failure -> state.errorMessage
                    ?: getString(R.string.generic_error_message)
            }
        }
    }
}