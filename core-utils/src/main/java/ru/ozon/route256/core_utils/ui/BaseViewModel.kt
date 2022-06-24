package ru.ozon.route256.core_utils.ui

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import ru.ozon.route256.core_utils.NoConnectionException
import ru.ozon.route256.core_utils.R

abstract class BaseViewModel : ViewModel() {

    sealed class Action {
        data class ShowToast(@StringRes val messageRes: Int) : Action()
    }

    sealed class State {
        object Init : State()
        object Alive : State()
    }

    protected val _state = MutableLiveData<State>(State.Init)
    val state: LiveData<State> = _state

    fun <T> T.asEvent() = Event(this)

    private fun getMessageExceptionRes(cause: Throwable): Int {
        return when (cause) {
            is NoConnectionException -> R.string.no_internet_connection
            else -> R.string.base_error
        }
    }

    protected val handlerException = CoroutineExceptionHandler { _, exception ->
        run {
            handleException(exception)
        }
    }

    protected open fun handleException(exception: Throwable) {
        val messageRes = getMessageExceptionRes(exception)
        _action.postValue(Action.ShowToast(messageRes).asEvent())
    }

    protected val _action = MutableLiveData<Event<Action>>()
    val action: LiveData<Event<Action>> = _action
}