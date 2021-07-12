package ru.sulgik.todo.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.sulgik.todo.*

val tasksModule = module {
    single { TasksDatabase.buildInstance(get()).tasksDao }
    factory <TasksRepository>{ RoomTasksRepository(get()) }
    viewModel<TasksViewModel> { LocalTasksViewModel(get()) }
}