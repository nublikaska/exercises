package com.nublikaska.exercises.modules.avatars.item

import androidx.annotation.DrawableRes
import com.nublikaska.exercises.base.ui.recycler.DiffListItemModel

class AvatarItemViewModel(

    @DrawableRes val iconResId: Int,
    val name: String

) : DiffListItemModel