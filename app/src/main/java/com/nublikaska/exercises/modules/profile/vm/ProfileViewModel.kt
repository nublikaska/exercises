package com.nublikaska.exercises.modules.profile.vm

import com.nublikaska.exercises.base.extensions.sumByFloat
import com.nublikaska.exercises.base.extensions.toNavEvent
import com.nublikaska.exercises.base.live_data.map
import com.nublikaska.exercises.base.ui.BaseViewModel
import com.nublikaska.exercises.modules.avatars.data.UserRepository
import com.nublikaska.exercises.modules.profile.ui.ProfileFragmentDirections
import com.nublikaska.exercises.modules.repository.test.TestRepository

class ProfileViewModel(

    userRepository: UserRepository,
    testRepository: TestRepository

) : BaseViewModel() {

    val userNameGreeting = "Привет, ${userRepository.userName}"

    val description = testRepository.getAllTests().map {

        val totalPoints = it.sumBy { test -> test.level }

        val currentPoints = it.filter { test -> test.correctAnswersPercent > 0 }
        .sumByFloat { test -> (test.correctAnswersPercent/100 * test.level) }
        .toInt()

        "Вы набрали $currentPoints поинтов из $totalPoints"
    }

    fun showAllPassedRules() {

        postViewEvents(ProfileFragmentDirections.actionProfileFragmentToRulesFragment().toNavEvent())
    }
}