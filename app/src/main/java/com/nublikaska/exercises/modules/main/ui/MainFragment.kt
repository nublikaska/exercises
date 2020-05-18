package com.nublikaska.exercises.modules.main.ui

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.nublikaska.exercises.R
import com.nublikaska.exercises.BR
import com.nublikaska.exercises.base.extensions.sharedPrefs
import com.nublikaska.exercises.base.extensions.viewModelCreator
import com.nublikaska.exercises.base.live_data.observeNotNull
import com.nublikaska.exercises.base.ui.BaseFragment
import com.nublikaska.exercises.modules.avatars.data.UserRepositoryImpl
import com.nublikaska.exercises.modules.main.vm.MainViewModel
import com.nublikaska.exercises.modules.repository.test.TestRepositoryImpl
import kotlinx.android.synthetic.main.fragment_main.*

internal class MainFragment : BaseFragment() {

    override val layoutId = R.layout.fragment_main
    override val dataBindingVariable = BR.vmMain
    override val viewModel by viewModelCreator {

        MainViewModel(
            userRepository = UserRepositoryImpl(sharedPrefs),
            testRepository = TestRepositoryImpl(sharedPrefs)
        )
    }

    override fun initViews(savedInstanceState: Bundle?) {
        super.initViews(savedInstanceState)

        val adapter = MainAdapter(viewModel)

        fragment_main_rv.layoutManager = GridLayoutManager(context, 1)
        fragment_main_rv.adapter = adapter

        observeNotNull(viewModel.tests) {

            adapter.updateItems(it)
        }
    }
}