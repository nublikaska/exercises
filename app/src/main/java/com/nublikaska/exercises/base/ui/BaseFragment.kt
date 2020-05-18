package com.nublikaska.exercises.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.nublikaska.exercises.base.extensions.weak
import com.nublikaska.exercises.base.live_data.observeNotNull

abstract class BaseFragment : Fragment() {

    @get:LayoutRes
    protected abstract val layoutId: Int
    protected abstract val dataBindingVariable: Int?

    protected abstract val viewModel: BaseViewModel

    /**
     * Должен ли фрагмент запрещать своим детям обрабатывать системный эвент нажатия кнопки (жеста) назад
     */
    protected open val stopOnBackPressedPropagation = true

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

        if (parentNotAllowHandleBackPress()) return
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, createOnBackPressedCallback())
    }

    private fun initViewModel() {

        dataBindingVariable?.let { binding?.setVariable(it, viewModel) }
        binding?.lifecycleOwner = viewLifecycleOwner
    }

    private fun subscribeOnEvents() {

        viewModel.viewEvent.observeNotNull(viewLifecycleOwner) { it.execute(this) }
    }

    private fun parentNotAllowHandleBackPress(): Boolean {

        return (parentFragment as? BaseFragment)?.stopOnBackPressedPropagation == true
    }

    private fun createOnBackPressedCallback() = object : OnBackPressedCallback(true) {

        override fun handleOnBackPressed() = onBackPressed()
    }

    protected open fun onBackPressed() = viewModel.onBackPressed()

    override fun onDestroyView() {

        binding = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(viewModel)
    }
}