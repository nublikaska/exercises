package com.nublikaska.exercises.modules.main.ui

import androidx.databinding.ViewDataBinding
import com.nublikaska.exercises.R
import com.nublikaska.exercises.BR
import com.nublikaska.exercises.base.ui.recycler.SingleItemLayoutDiffListAdapter
import com.nublikaska.exercises.modules.main.vm.item.MainItemViewModel
import com.nublikaska.exercises.modules.main.vm.item.MainTestItemClickListener

class MainAdapter(

    private val clickListener: MainTestItemClickListener

) : SingleItemLayoutDiffListAdapter<MainItemViewModel>(

    itemLayoutId = R.layout.li_main,
    dataBindingVariable = BR.vmMainItem
) {

    override fun setVariables(binding: ViewDataBinding, item: MainItemViewModel) {
        super.setVariables(binding, item)

        binding.setVariable(BR.clickListener, clickListener)
    }
}