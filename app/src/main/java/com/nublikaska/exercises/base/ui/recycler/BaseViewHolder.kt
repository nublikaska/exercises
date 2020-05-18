package com.nublikaska.exercises.base.ui.recycler

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder(

    val binding: ViewDataBinding,
    val viewType: Int

) : RecyclerView.ViewHolder(binding.root), LifecycleOwner {

    private var isWasAttached = false

    private var lifecycleRegistry = LifecycleRegistry(this)

    internal fun markAttach() {

        if (isWasAttached) lifecycleRegistry = LifecycleRegistry(this)

        isWasAttached = true

        lifecycleRegistry.currentState = Lifecycle.State.STARTED

        binding.setLifecycleOwner { lifecycleRegistry }
    }

    internal fun markDetach() {

        lifecycleRegistry.currentState = Lifecycle.State.DESTROYED
    }

    final override fun getLifecycle(): Lifecycle = lifecycleRegistry
}