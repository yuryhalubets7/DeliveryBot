package com.yuryhalubets.deliverybot

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuryhalubets.bot.Bot
import com.yuryhalubets.bot.factory.BotFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val bot: Bot by lazy { BotFactory.create() }

    private val _viewStateFlow: MutableStateFlow<MainViewState> =
        MutableStateFlow(MainViewState.Success(""))
    val viewStateFlow: StateFlow<MainViewState> = _viewStateFlow.asStateFlow()

    /**
     * Call [Bot] to find path
     * */
    fun findPath(input: String) = viewModelScope.launch(Dispatchers.IO) {
        _viewStateFlow.value = MainViewState.Loading
        when (val result = bot.execute(input)) {
            is Bot.Result.Success -> _viewStateFlow.value = MainViewState.Success(result.path)
            is Bot.Result.Failure ->
                _viewStateFlow.value = MainViewState.Failure(result.cause.localizedMessage)
        }
    }
}
