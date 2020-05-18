package com.nublikaska.exercises.base.db.test

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.nublikaska.exercises.base.db.converter.StringConverters
import java.util.UUID

@Entity
class QuestionDb {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    @ColumnInfo(name = TEST_ID)
    var testId: String = UUID.randomUUID().toString()

    var questionText: String = ""
    var correctAnswer: String = ""
    var iconResId: Int? = null

    @TypeConverters(StringConverters::class)
    var answerOptions: List<String> = emptyList()

    companion object {

        const val TEST_ID = "test_id"
    }
}