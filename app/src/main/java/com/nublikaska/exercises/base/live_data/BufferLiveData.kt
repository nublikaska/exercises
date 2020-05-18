package com.nublikaska.exercises.base.live_data

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.Queue
import java.util.concurrent.LinkedBlockingQueue

class BufferLiveData<T> : MutableLiveData<T>() where T : Any {

    private val buffer: Queue<T> = LinkedBlockingQueue<T>()

    private val mediatorLiveData = MediatorLiveData<T>()
    private val fakeObserver = Observer<T> {}

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {

        super.observe(owner, Observer {

            if (buffer.isNotEmpty()) drainBuffer(observer)
        })
    }

    private fun drainBuffer(observer: Observer<in T>) {

        var element = buffer.poll()
        while (element != null) {
            observer.onChanged(element)
            element = buffer.poll()
        }
    }

    override fun onActive() {
        super.onActive()
        mediatorLiveData.observeForever(fakeObserver)
    }

    override fun onInactive() {
        super.onInactive()
        mediatorLiveData.removeObserver(fakeObserver)
    }

    override fun setValue(value: T?) {

        buffer.add(value)
        super.setValue(value)
    }

    fun merge(liveData: LiveData<T>) = this.apply {

        mediatorLiveData.addSource(liveData) { value = it }
    }
}