package com.excercisegenpackone.excercisegenbiceps.recycler

import kotlin.random.Random

data class ExerciseItem(
    val id: Long = Random.nextLong(from = 0, until = 100000),
    val name: String,
    val equipment: String
)
