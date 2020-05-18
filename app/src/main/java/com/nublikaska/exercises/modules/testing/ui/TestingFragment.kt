package com.nublikaska.exercises.modules.testing.ui

import android.os.Bundle
import androidx.navigation.NavDirections
import androidx.navigation.fragment.navArgs
import com.nublikaska.exercises.BR
import com.nublikaska.exercises.R
import com.nublikaska.exercises.base.extensions.sharedPrefs
import com.nublikaska.exercises.base.extensions.viewModelCreator
import com.nublikaska.exercises.base.navigation.nested.BaseWrapperFragment
import com.nublikaska.exercises.modules.testing.vm.TestRuleItemViewModel
import com.nublikaska.exercises.modules.testing.vm.TestingViewModelFactory
import kotlinx.android.synthetic.main.view_rules.*

internal class TestingFragment : BaseWrapperFragment() {

    private val args by navArgs<TestingFragmentArgs>()

    override val startNavDirections: NavDirections by lazy {

        TestingFragmentDirections.toQuestionFragment(
            question = args.test.questions.first(),
            isLastQuestion = args.test.questions.count() == 1
        )
    }
    override val containerId = R.id.fragment_testing_question_container

    override val layoutId = R.layout.fragment_testing
    override val dataBindingVariable = BR.vmTesting
    override val viewModel by viewModelCreator(
        TestingViewModelFactory(

            sharedPrefs = lazy { sharedPrefs },
            test = lazy { args.test }
        )
    )

    override fun initViews(savedInstanceState: Bundle?) {
        super.initViews(savedInstanceState)

        val adapter = RulesAdapter()
        fragment_testing_rv?.adapter = adapter
        adapter.updateItems(args.test.rules.map(::TestRuleItemViewModel))
    }
}