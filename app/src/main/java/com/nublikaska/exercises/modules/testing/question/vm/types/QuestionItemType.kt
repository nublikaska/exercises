package com.nublikaska.exercises.modules.testing.question.vm.types

import androidx.annotation.LayoutRes
import com.nublikaska.exercises.R

enum class QuestionItemType(@LayoutRes val layoutResId: Int) {

    ANSWER_OPTIONS_QUESTION(R.layout.fragment_test_answer_options_question),

    ANSWER_INPUT_QUESTION(R.layout.fragment_test_answer_input_question)
}