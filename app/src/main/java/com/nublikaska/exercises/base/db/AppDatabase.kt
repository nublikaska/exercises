package com.nublikaska.exercises.base.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nublikaska.exercises.base.db.converter.StringConverters
import com.nublikaska.exercises.base.db.test.QuestionDb
import com.nublikaska.exercises.base.db.test.TestDao
import com.nublikaska.exercises.base.db.test.TestDb

@Database(entities = [TestDb::class, QuestionDb::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun testDao(): TestDao
}