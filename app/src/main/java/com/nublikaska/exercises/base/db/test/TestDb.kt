package com.nublikaska.exercises.base.db.test

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.nublikaska.exercises.base.db.converter.StringConverters
import com.nublikaska.exercises.base.db.test.TestDb.Companion.NAME
import com.nublikaska.exercises.base.test.TestType
import java.util.UUID

@Entity(tableName = NAME)
class TestDb {

    @PrimaryKey
    @ColumnInfo(name = ID)
    var id: String = UUID.randomUUID().toString()

    var name: String = ""

    var description: String = ""

    var level: Int = 0

    var type: String = TestType.DEFAULT.name

    var information: String? = null

    @TypeConverters(StringConverters::class)
    var rules: List<String> = emptyList()

    var passed: Boolean = false

    var correctAnswersPercent: Float = 0f

    companion object {

        const val NAME = "TestDb"
        const val ID = "id"
        const val PASSED = "passed"
        const val CORRECT_ANSWER_PERCENT = "correctAnswersPercent"
    }
}