package com.nublikaska.exercises.modules.profile.ui

import android.media.AudioManager
import android.os.Bundle
import android.widget.SeekBar
import com.nublikaska.exercises.BR
import com.nublikaska.exercises.R
import com.nublikaska.exercises.base.extensions.sharedPrefs
import com.nublikaska.exercises.base.extensions.viewModelCreator
import com.nublikaska.exercises.base.ui.BaseFragment
import com.nublikaska.exercises.modules.avatars.data.UserRepositoryImpl
import com.nublikaska.exercises.modules.profile.vm.ProfileViewModel
import com.nublikaska.exercises.modules.repository.test.TestRepositoryImpl
import com.nublikaska.exercises.ui_kit.extensions.audioManager
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : BaseFragment() {

    override val layoutId = R.layout.fragment_profile
    override val dataBindingVariable = BR.vmProfile
    override val viewModel by viewModelCreator {

        ProfileViewModel(
            userRepository = UserRepositoryImpl(sharedPrefs),
            testRepository = TestRepositoryImpl(sharedPrefs)
        )
    }

    override fun initViews(savedInstanceState: Bundle?) {
        super.initViews(savedInstanceState)

        fragment_profile_volume_seek_bar.progress = requireContext()
            .audioManager?.getStreamVolume(AudioManager.STREAM_MUSIC)
            ?: 0

        fragment_profile_volume_seek_bar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) = Unit
            override fun onStartTrackingTouch(seekBar: SeekBar) = Unit
            override fun onStopTrackingTouch(seekBar: SeekBar) {

                requireContext().audioManager?.setStreamVolume(AudioManager.STREAM_MUSIC, seekBar.progress, AudioManager.FLAG_SHOW_UI)
            }
        })
    }
}