package com.nublikaska.exercises.modules.repository.test

import androidx.lifecycle.LiveData
import com.nublikaska.exercises.base.test.Test

interface TestRepository {

    var trainingСompleted: Boolean

    val currentLevel: LiveData<Int>

    fun upCurrentLevel()

    fun clearLevel()

    fun getAllTests(): LiveData<List<Test>>

    fun getAllPassedRules(): LiveData<List<String>>

    suspend fun getTestById(id: String): Test?

    suspend fun saveTests(tests: List<Test>)

    suspend fun updateByIds(passed: Boolean,
                            correctAnswersPercent: Float,
                            ids: List<String>)
}