package com.nublikaska.exercises.base.ui.recycler

import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding

open class SingleItemLayoutDiffListAdapter<T : DiffListItemModel>(

    @LayoutRes private val itemLayoutId: Int,
    private val dataBindingVariable: Int

) : DiffListAdapter<T>() {

    final override fun getListItemLayoutId(type: Int) = itemLayoutId

    final override fun fillHolderViews(holder: BaseViewHolder?,
                                       item: T,
                                       itemPosition: Int,
                                       viewType: Int,
                                       payloads: MutableList<Any>?) {

        holder?.binding?.let {
            setVariables(it, item)
            it.executePendingBindings()
        }
    }

    @CallSuper
    open fun setVariables(binding: ViewDataBinding, item: T) {

        binding.setVariable(dataBindingVariable, item)
    }
}