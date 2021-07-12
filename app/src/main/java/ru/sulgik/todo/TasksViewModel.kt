package ru.sulgik.todo

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import ru.sulgik.todo.data.Task

abstract class TasksViewModel: ViewModel() {

    abstract val allTasks: Flow<List<Task>>

    abstract fun insertTask(task: Task)

    abstract fun updateTask(task: Task)

    abstract fun deleteById(id: Long)

}