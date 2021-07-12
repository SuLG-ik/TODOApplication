package ru.sulgik.todo

import kotlinx.coroutines.flow.Flow
import ru.sulgik.todo.data.Task

interface TasksRepository {


    val allTasks: Flow<List<Task>>

    suspend fun insertTask(task: Task)

    suspend fun updateTask(task: Task)

    suspend fun deleteById(id: Long)

}