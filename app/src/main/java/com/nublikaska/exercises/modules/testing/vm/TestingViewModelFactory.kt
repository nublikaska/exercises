package com.nublikaska.exercises.modules.testing.vm

import com.nublikaska.exercises.base.store.shared_preferences.SharedPrefs
import com.nublikaska.exercises.base.test.Test
import com.nublikaska.exercises.base.ui.ViewModelFactory
import com.nublikaska.exercises.modules.avatars.data.UserRepositoryImpl

internal class TestingViewModelFactory(

    private val sharedPrefs: Lazy<SharedPrefs>,
    private val test: Lazy<Test>

) : ViewModelFactory<TestingViewModel> {

    override fun invoke() = TestingViewModel(

        userRepository = UserRepositoryImpl(sharedPrefs.value),
        test = test.value
    )
}