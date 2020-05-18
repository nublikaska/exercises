package com.nublikaska.exercises.modules.auth.ui

import com.nublikaska.exercises.BR
import com.nublikaska.exercises.R
import com.nublikaska.exercises.base.extensions.sharedPrefs
import com.nublikaska.exercises.base.extensions.viewModelCreator
import com.nublikaska.exercises.base.ui.BaseFragment
import com.nublikaska.exercises.modules.auth.vm.AuthViewModel
import com.nublikaska.exercises.modules.avatars.data.UserRepositoryImpl

internal class AuthFragment : BaseFragment() {

    override val layoutId: Int = R.layout.fragment_auth
    override val dataBindingVariable = BR.vmAuth

    override val viewModel by viewModelCreator {

        AuthViewModel(UserRepositoryImpl(sharedPrefs))
    }
}
