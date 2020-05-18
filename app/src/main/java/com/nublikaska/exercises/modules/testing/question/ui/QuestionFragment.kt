package com.nublikaska.exercises.modules.testing.question.ui

import android.os.Bundle
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.navigation.fragment.navArgs
import com.nublikaska.exercises.BR
import com.nublikaska.exercises.base.extensions.viewModelCreator
import com.nublikaska.exercises.base.ui.BaseFragment
import com.nublikaska.exercises.modules.testing.question.mapper.TestQuestionToItemTypeMapper
import com.nublikaska.exercises.modules.testing.question.mapper.TestQuestionToViewModelMapper
import com.nublikaska.exercises.modules.testing.question.vm.QuestionViewModel
import com.nublikaska.exercises.modules.testing.question.vm.types.OptionAnswersQuestionViewModel
import com.nublikaska.exercises.modules.testing.question.vm.types.QuestionItemType
import com.nublikaska.exercises.ui_kit.views.chip.KitRadioChip
import kotlinx.android.synthetic.main.fragment_test_answer_options_question.*

internal class QuestionFragment : BaseFragment() {

    private val args by navArgs<QuestionFragmentArgs>()

    private val questionToItemTypeMapper = TestQuestionToItemTypeMapper()
    private val viewModelMapper = TestQuestionToViewModelMapper()

    private val questionType by lazy { questionToItemTypeMapper.invoke(args.question) }

    override val layoutId by lazy { questionType.layoutResId }
    override val dataBindingVariable = BR.vmQuestion
    override val viewModel by viewModelCreator<QuestionViewModel> { viewModelMapper.invoke(args.question) }

    override fun initViews(savedInstanceState: Bundle?) {
        super.initViews(savedInstanceState)

        initBtnText()

        val vm = viewModel

        if (vm is OptionAnswersQuestionViewModel && questionType == QuestionItemType.ANSWER_OPTIONS_QUESTION) {

            fragment_test_answer_options_question_chip_group?.apply {

                vm.question.answerOptions.forEach { answer ->

                    addView(
                        KitRadioChip(context).apply {
                            text = answer
                            setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
                                if (isChecked) vm.onSelectAnswer(answer)
                            }
                            layoutParams = ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                            )
                        }
                    )
                }
            }
        }
    }

    private fun initBtnText() {

        fragment_question_bottom_btn.text = when (args.isLastQuestion) {

            true -> "Закончить тест"
            else -> "К следующему вопросу"
        }
    }

    override fun onBackPressed() = Unit
}