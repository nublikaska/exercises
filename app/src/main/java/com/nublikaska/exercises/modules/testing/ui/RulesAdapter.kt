package com.nublikaska.exercises.modules.testing.ui

import com.nublikaska.exercises.R
import com.nublikaska.exercises.BR
import com.nublikaska.exercises.base.ui.recycler.SingleItemLayoutDiffListAdapter
import com.nublikaska.exercises.modules.testing.vm.TestRuleItemViewModel

class RulesAdapter : SingleItemLayoutDiffListAdapter<TestRuleItemViewModel>(

    itemLayoutId = R.layout.li_test_rule,
    dataBindingVariable = BR.vmTestRuleItem
)