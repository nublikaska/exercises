package com.nublikaska.exercises.base.test

import java.util.LinkedList

class TestBuilder {

    private var information: String? = null

    private val rules = LinkedList<String>()

    private val questions = LinkedList<TestQuestion>()

    private var name: String = ""

    private var description: String = ""

    private var level = 0

    private var type = TestType.DEFAULT

    fun addInformation(information: String) = apply { this.information = information }

    fun addRule(rule: String) = apply { rules.add(rule) }

    fun addQuestion(question: TestQuestion) = apply { questions.add(question) }

    fun setName(name: String) = apply { this.name = name }

    fun setDescription(description: String) = apply { this.description = description }

    fun setLevel(level: Int): TestBuilder = this.apply { this.level = level }

    fun setType(type: TestType): TestBuilder = this.apply { this.type = type }

    fun build(id: String) = Test(
        id = id,
        name = name,
        description = description,
        imageUrl = "http://dotnetuniversity.com/wp-content/uploads/2019/12/usertesting-730x356.jpg",
        level = level,
        type = type,
        passed = false,
        information = information,
        rules = rules,
        questions = questions
    )
}