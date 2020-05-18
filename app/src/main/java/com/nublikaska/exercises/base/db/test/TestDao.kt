package com.nublikaska.exercises.base.db.test

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface TestDao {

    @Transaction
    @Query("SELECT * FROM ${TestDb.NAME}")
    fun getAllTestsWithQuestions(): LiveData<List<TestWithQuestionDb>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllTests(items: List<TestDb>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllQuestions(items: List<QuestionDb>)

    @Query("UPDATE ${TestDb.NAME} SET ${TestDb.PASSED} = :passed, ${TestDb.CORRECT_ANSWER_PERCENT} = :correctAnswersPercent WHERE ${TestDb.ID} in(:ids)")
    fun updatePassedByIds(passed: Boolean, correctAnswersPercent: Float, ids: List<String>): Int

    @Query("SELECT * FROM ${TestDb.NAME} WHERE ${TestDb.ID} = :id")
    fun getById(id: String): TestWithQuestionDb?
}