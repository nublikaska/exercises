package com.nublikaska.exercises.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nublikaska.exercises.base.extensions.weak
import com.nublikaska.exercises.base.live_data.observeNotNull

internal abstract class BaseBottomSheetDialogFragment : BottomSheetDialogFragment() {

    @get:LayoutRes
    protected abstract val layoutId: Int
    protected abstract val dataBindingVariable: Int?

    protected abstract val viewModel: BaseViewModel

    private var binding: ViewDataBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)

        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(savedInstanceState)
    }

    @CallSuper
    protected open fun initViews(savedInstanceState: Bundle?) {

        viewModel.sharedViewModelProvider = SharedViewModelProvider(weak())

        lifecycle.addObserver(viewModel)
        initViewModel()
        subscribeOnEvents()
    }

    private fun initViewModel() {

        dataBindingVariable?.let { binding?.setVariable(it, viewModel) }
        binding?.lifecycleOwner = viewLifecycleOwner
    }

    private fun subscribeOnEvents() {

        viewModel.viewEvent.observeNotNull(viewLifecycleOwner) { it.execute(this) }
    }

    override fun onDestroyView() {

        binding = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(viewModel)
    }
}