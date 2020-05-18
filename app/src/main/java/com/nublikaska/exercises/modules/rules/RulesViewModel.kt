package com.nublikaska.exercises.modules.rules

import com.nublikaska.exercises.base.live_data.map
import com.nublikaska.exercises.base.ui.BaseViewModel
import com.nublikaska.exercises.modules.repository.test.TestRepository

class RulesViewModel(

    testRepository: TestRepository

) : BaseViewModel() {

    val rules = testRepository.getAllPassedRules()
    val isEmptyState = rules.map { it.isEmpty() }
}