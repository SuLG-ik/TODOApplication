package ru.sulgik.todo

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import ru.sulgik.todo.data.Task

class LocalTasksViewModel(private val tasksRepository: TasksRepository) : TasksViewModel() {
    override val allTasks: Flow<List<Task>> = tasksRepository.allTasks

    override fun insertTask(task: Task) {
        viewModelScope.launch {
            tasksRepository.insertTask(task)
        }
    }

    override fun updateTask(task: Task) {
        viewModelScope.launch {
            tasksRepository.updateTask(task)
        }
    }

    override fun deleteById(id: Long) {
        viewModelScope.launch {
            tasksRepository.deleteById(id)
        }
    }
}