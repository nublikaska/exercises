package com.nublikaska.exercises.modules.avatars

import com.nublikaska.exercises.base.ui.BaseViewModel
import com.nublikaska.exercises.modules.avatars.data.UserRepository
import com.nublikaska.exercises.modules.avatars.item.AvatarItemClickListener
import com.nublikaska.exercises.modules.avatars.item.AvatarItemViewModel

internal class AvatarsViewModel : BaseViewModel(), AvatarItemClickListener {

    override fun onClick(item: AvatarItemViewModel) {

        backWithResult(item.iconResId)
    }
}