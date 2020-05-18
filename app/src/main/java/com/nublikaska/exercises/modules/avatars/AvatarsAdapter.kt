package com.nublikaska.exercises.modules.avatars

import androidx.databinding.ViewDataBinding
import com.nublikaska.exercises.R
import com.nublikaska.exercises.BR
import com.nublikaska.exercises.base.ui.recycler.SingleItemLayoutDiffListAdapter
import com.nublikaska.exercises.modules.avatars.item.AvatarItemClickListener
import com.nublikaska.exercises.modules.avatars.item.AvatarItemViewModel

internal class AvatarsAdapter(

    private val clickListener: AvatarItemClickListener

) : SingleItemLayoutDiffListAdapter<AvatarItemViewModel>(
    dataBindingVariable = BR.vmAvatarItem,
    itemLayoutId = R.layout.li_avatar
) {

    override fun setVariables(binding: ViewDataBinding, item: AvatarItemViewModel) {
        super.setVariables(binding, item)

        binding.setVariable(BR.clickListener, clickListener)
    }
}