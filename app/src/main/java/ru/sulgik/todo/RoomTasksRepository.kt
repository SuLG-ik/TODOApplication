package ru.sulgik.todo

import kotlinx.coroutines.flow.Flow
import ru.sulgik.todo.data.Task

class RoomTasksRepository(private val tasksDAO: TasksDAO) : TasksRepository {
    override val allTasks: Flow<List<Task>> = tasksDAO.getAllTasks()

    override suspend fun insertTask(task: Task) {
        tasksDAO.insertTask(task)
    }

    override suspend fun updateTask(task: Task) {
        tasksDAO.updateTask(task)
    }

    override suspend fun deleteById(id: Long) {
        tasksDAO.deleteById(id)
    }
}