package com.nublikaska.exercises.base.ui

import androidx.annotation.CallSuper
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.nublikaska.exercises.base.event.NavEvent
import com.nublikaska.exercises.base.event.ViewEvent
import com.nublikaska.exercises.base.extensions.setResultToPreviousBackStackEntry
import com.nublikaska.exercises.base.live_data.BufferLiveData
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(),
    LifecycleObserver,
    LifecycleOwner,
    CoroutineScope {

    private val lifecycleRegistry = LifecycleRegistry(this)

    private val supervisorJob = SupervisorJob()

    private val _viewEvent = BufferLiveData<ViewEvent>()

    val viewEvent: LiveData<ViewEvent>
        get() = _viewEvent

    final override val coroutineContext: CoroutineContext = Dispatchers.Main + supervisorJob +
            CoroutineExceptionHandler { _, throwable -> handleCoroutineException(throwable) }

    @PublishedApi
    internal lateinit var sharedViewModelProvider: SharedViewModelProvider

    init {
        lifecycleRegistry.currentState = Lifecycle.State.STARTED
    }

    @CallSuper
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    protected open fun onCreate() = Unit

    final override fun getLifecycle(): Lifecycle = lifecycleRegistry

    @CallSuper
    protected open fun handleCoroutineException(throwable: Throwable) = Unit

    protected fun postViewEvents(vararg viewEvent: ViewEvent) {

        viewEvent.forEach(_viewEvent::setValue)
    }

    protected fun <T> BaseViewModel.backWithResult(result: T) {

        setResultToPreviousBackStackEntry(result)
        postViewEvents(NavEvent.Back)
    }

    open fun onBackPressed() = postViewEvents(NavEvent.Back)

    @CallSuper
    override fun onCleared() {
        super.onCleared()

        lifecycleRegistry.currentState = Lifecycle.State.DESTROYED

        supervisorJob.cancel()
    }
}