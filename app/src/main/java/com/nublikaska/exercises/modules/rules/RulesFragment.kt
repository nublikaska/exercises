package com.nublikaska.exercises.modules.rules

import android.os.Bundle
import com.nublikaska.exercises.BR
import com.nublikaska.exercises.R
import com.nublikaska.exercises.base.extensions.sharedPrefs
import com.nublikaska.exercises.base.extensions.viewModelCreator
import com.nublikaska.exercises.base.live_data.observeNotNull
import com.nublikaska.exercises.base.ui.BaseFragment
import com.nublikaska.exercises.modules.repository.test.TestRepositoryImpl
import com.nublikaska.exercises.modules.testing.ui.RulesAdapter
import com.nublikaska.exercises.modules.testing.vm.TestRuleItemViewModel
import kotlinx.android.synthetic.main.fragment_rules.*

class RulesFragment : BaseFragment() {

    override val layoutId = R.layout.fragment_rules
    override val dataBindingVariable = BR.vmRules
    override val viewModel by viewModelCreator { RulesViewModel(TestRepositoryImpl(sharedPrefs)) }

    override fun initViews(savedInstanceState: Bundle?) {
        super.initViews(savedInstanceState)

        val adapter = RulesAdapter()
        fragment_rules_rv?.adapter = adapter
        viewModel.rules.observeNotNull(this) {

            adapter.updateItems(it.map(::TestRuleItemViewModel))
        }
    }
}