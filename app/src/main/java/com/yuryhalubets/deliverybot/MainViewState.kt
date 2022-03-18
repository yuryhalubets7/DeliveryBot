package com.yuryhalubets.deliverybot

/**
 * Present all states of [MainActivity]
 * */
sealed class MainViewState {

    /**
     * Loading state
     * */
    object Loading : MainViewState()

    /**
     * Success state that contain found path
     * */
    data class Success(val path: String) : MainViewState()

    /**
     * Failure state with errorMessage to show for user
     * */
    data class Failure(val errorMessage: String?) : MainViewState()
}
