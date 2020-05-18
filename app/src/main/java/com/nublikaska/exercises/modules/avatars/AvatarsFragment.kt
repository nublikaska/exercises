package com.nublikaska.exercises.modules.avatars

import android.os.Bundle
import com.nublikaska.exercises.BR
import com.nublikaska.exercises.R
import com.nublikaska.exercises.base.extensions.sharedPrefs
import com.nublikaska.exercises.base.extensions.viewModelCreator
import com.nublikaska.exercises.base.ui.BaseBottomSheetDialogFragment
import com.nublikaska.exercises.modules.avatars.item.AvatarItemViewModel
import kotlinx.android.synthetic.main.fragment_avatars.*

internal class AvatarsFragment : BaseBottomSheetDialogFragment() {

    override val layoutId = R.layout.fragment_avatars
    override val dataBindingVariable = BR.vmAvatars
    override val viewModel by viewModelCreator<AvatarsViewModel>()

    override fun initViews(savedInstanceState: Bundle?) {
        super.initViews(savedInstanceState)

        fragment_avatars_rv.adapter = AvatarsAdapter(viewModel).apply {

            updateItems(

                listOf(
                    AvatarItemViewModel(
                        iconResId = R.drawable.ic_symptoms,
                        name = requireContext().getString(R.string.avatar_name_male)
                    ),
                    AvatarItemViewModel(
                        iconResId = R.drawable.ic_quarantine,
                        name = requireContext().getString(R.string.avatar_name_female)
                    )
                )
            )
        }
    }
}