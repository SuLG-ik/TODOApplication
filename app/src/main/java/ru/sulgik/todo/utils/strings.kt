package ru.sulgik.todo.utils

fun Any?.toStringOrEmpty(): String {
    return this?.toString().orEmpty()
}