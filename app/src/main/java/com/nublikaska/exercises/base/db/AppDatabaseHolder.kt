package com.nublikaska.exercises.base.db

import android.content.Context
import androidx.room.Room
import java.lang.NullPointerException

object AppDatabaseHolder {

    private var _instance: AppDatabase? = null

    val instance: AppDatabase
        get() = _instance ?: throw NullPointerException("Нельзя получить инстанс БД до того, как он будет создан")

    fun initDb(context: Context) {

        _instance = Room
            .databaseBuilder(
                context,
                AppDatabase::class.java,
                "exercisesDatabase"
            )
            .build()
    }
}