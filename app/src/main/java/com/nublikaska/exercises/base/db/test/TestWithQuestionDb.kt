package com.nublikaska.exercises.base.db.test

import androidx.room.Embedded
import androidx.room.Relation

class TestWithQuestionDb {

    @Embedded
    lateinit var test: TestDb

    @Relation(parentColumn = TestDb.ID, entity = QuestionDb::class, entityColumn = QuestionDb.TEST_ID)
    lateinit var questions: List<QuestionDb>
}